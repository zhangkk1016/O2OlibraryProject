package cn.hnust.book.model;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.dao.BookLibrary;
import cn.hnust.book.dao.MyList;
import cn.hnust.book.dao.Transaction;

public class ComfirmService {
	
	public JSONObject borrowerComfirm(int studentId, int bookId) throws JSONException{		
		JSONObject result = new JSONObject();
		Transaction transaction = new Transaction();
		result = transaction.comfirmByBorrower(bookId, studentId);
		return result;
	}
	
	public JSONObject ownnerComfirm(int studentId, int bookId)throws JSONException{		
		JSONObject result = new JSONObject();
		Transaction transaction = new Transaction();
		MyList myList = new MyList();
		int status = 2;
		boolean flag = myList.updateStatus(bookId, status);
		try {
			if(flag == true){
				result = transaction.comfirmByOwnner(bookId, studentId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject borrowerFinish(int studentId, int bookId) throws JSONException{
		JSONObject result = new JSONObject();
		Transaction transaction = new Transaction();
		try {
			result = transaction.finishByBorrower(bookId, studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject ownnerFinish(int studentId, int bookId) throws JSONException, SQLException{
		JSONObject result = new JSONObject();
		Transaction transaction = new Transaction();
		BookLibrary bookLibrary = new BookLibrary();
		MyList myList = new MyList();
		boolean flag1 = false;
		int flag = 0;	
		int originalStatus = transaction.queryStatus(bookId, studentId);
		int receiveId = transaction.findReceiveId(bookId, studentId);
		JSONObject temp = myList.queryCoordinate(receiveId);
		double longitude = temp.getDouble("longitude0");
		double latitude = temp.getDouble("latitude0");
		String address = temp.getString("address");
		int bookStatus = 1;//Êé¼®×´Ì¬ 
		if(originalStatus == 2){
			flag1 = bookLibrary.updateOwnner(bookId, receiveId, longitude, latitude, address);
			if(flag1){
				int status = 3;//½»Ò××´Ì¬
				flag = transaction.updateStatus(bookId, studentId,status);
				if(flag == 1){
					myList.updateStatus(bookId, bookStatus);
					result.put("result", "Success");
					result.put("status",status);					
				}
				else{
					result.put("result", "Wrong");
					result.put("Message", "There have no record in transaction");
				}
			}
			else{
				result.put("result", "Wrong");
				result.put("Message", "update book_information failed");
			}
		}
		else{
			result.put("result", "Wrong");
			result.put("Message", "please wait the borrower comfirm");
		}		
		return result;
	}
}
