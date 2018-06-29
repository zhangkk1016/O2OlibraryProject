package cn.hnust.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import cn.hnust.book.util.DBConnection;
import cn.hnust.book.util.DateTimeInServer;

public class Transaction {
	String tab = "transaction_information";
	
	//向交易信息表中插入数据
	public int InsertTransation(int bookId,int startUserId,int receiveUserId){
		int flag = 0;
		//向交易信息表中插入数据
		DBConnection db = new DBConnection();
		
		String startTime = new DateTimeInServer().getDateTime();
		
		System.out.println("StartTime:"+ startTime);
		int status = 0;
		String sql = "insert into "+ tab +"(book_id, start_time, receive_user_id, origin_user_id," +
				" status) values ('"+ bookId +"', '"+ startTime +"', '"+ startUserId +"', '" +
						""+ receiveUserId +"', '"+ status +"');";
		System.out.println(sql);
		flag = db.execute(sql);
		db.close();
		return flag;
	}
	
	//通过bookId查询交易状态
	public JSONObject queryStatus(int bookId) throws JSONException{
		JSONObject result = new JSONObject();
		DBConnection db = new DBConnection();
		String sql = "select status from "+ tab +" where book_id = '"+ bookId +"';";
		ResultSet rs =  null;
		try {
			rs = db.executeQuery(sql);
			if(rs.next()){
				result.put("status", rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return result;
	}
	
	//查询通知列表
	public JSONArray queryList(int studentId) throws SQLException{
		//查询通知列表
		JSONArray result = new JSONArray();
		DBConnection con1 = new DBConnection();
		DBConnection con2 = new DBConnection();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String sql1 = "select book_id, status from "+ tab +" where origin_user_id = '"
		+ studentId +"';";
		String sql2 = "select book_id, status from "+ tab +" where receive_user_id = '"
				+ studentId +"';";
		rs1 = con1.executeQuery(sql1);
		rs2 = con2.executeQuery(sql2);
		try {
			while(rs1.next()){
				JSONObject obj = new JSONObject();
				int bookId = rs1.getInt("book_id");
				int status = rs1.getInt("status");
				int flag = 1;
				obj.put("bookId", bookId);			
				obj.put("status", status);
				obj.put("flag", flag);
				result.put(obj);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con1.close();
		}
		try {
			while(rs2.next()){
				JSONObject obj = new JSONObject();
				int bookId = rs2.getInt("book_id");
				int status = rs2.getInt("status");
				int flag = 0;
				obj.put("bookId", bookId);			
				obj.put("status", status);
				obj.put("flag", flag);
				result.put(obj);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con2.close();
		}
		return result;
	}
	
	//借书者确认借书
	public JSONObject comfirmByBorrower(int bookId, int studentId) throws JSONException{
		JSONObject result = new JSONObject();
		DBConnection db = new DBConnection();
		int status = 1;
		String sql = "update "+ tab +" set status = '"+ status +"' " +
				"where receive_user_id = '"+ studentId +"' and book_id = '"+ bookId +"';";
		System.out.println(sql);
		int flag = db.execute(sql);
		if(flag == 1){
			result.put("result","Success");
			result.put("status", status);
		}
		else{
			result.put("result", "Wrong");
		}
		db.close();
		return result;
	}

	@Test
	//拥有者确认借书
	public JSONObject comfirmByOwnner(int bookId, int studentId) throws JSONException, SQLException {
		JSONObject result = new JSONObject();
		DBConnection db2 = new DBConnection();
		int status1 = 1;
		String sql2 = "update "+ tab +" set status = '"+ status1 +"' where origin_user_id = '"+ studentId +"' and" +
				" book_id = '"+ bookId +"';";
		int flag = db2.execute(sql2);
		if(flag == 1){
			result.put("status", status1);
			result.put("result", "Success");
		}
		else {
			result.put("result", "Wrong");
			result.put("Message", "db update false");
		}				
		db2.close();
		return result;
	}
	
	//借书者完成借书
	public JSONObject finishByBorrower(int bookId, int studentId) throws SQLException, JSONException {
		JSONObject result = new JSONObject();
		DBConnection db1 = new DBConnection();
		DBConnection db2 = new DBConnection();
		ResultSet rs = null;
		int status1 = 2;
		String sql1 = "select status from "+ tab +" where" +
				" receive_user_id = '"+ studentId +"' and book_id = '"+ bookId +"';";
		String sql2 = "update "+ tab +" set status = '"+ status1 +"' where receive_user_id = '"+ studentId +"' and book_id = '"+ bookId +"';";
		System.out.println(sql1);
		rs = db1.executeQuery(sql1);
		
		if(rs.next()){
			int status = rs.getInt("status");
			if(status == 1){
				int flag = db2.execute(sql2);
				if(flag == 1){
					result.put("status", status1);
					result.put("result", "Success");
				}
				else {
					result.put("result", "Wrong");
					result.put("Message", "db update false");
				}				
			}
			else{
				result.put("result", "Wrong");
				result.put("Message", "please wait the ownner comfirm to borrow");
			}
		}
		else{
			result.put("result", "Wrong");
		}
		db1.close();
		db2.close();
		return result;
	}
	
	//查询目前状态
	public int queryStatus(int bookId, int studentId) throws SQLException, JSONException {
		int result = -1;
		DBConnection db1 = new DBConnection();
		ResultSet rs1 = null;
		String sql1 = "select status from "+ tab +" where" +
				" origin_user_id = '"+ studentId +"' and book_id = '"+ bookId +"';";		
		rs1 = db1.executeQuery(sql1);
		if(rs1.next()){
			result = rs1.getInt("status");
		}		
		db1.close();
		return result;
	}
	
	//更新状态
	public int updateStatus(int bookId, int studentId, int status){
		int result = 0;
		DBConnection db = new DBConnection();
		String sql = "update "+ tab +" set status = '"+ status +"'where book_id = '"
				+ bookId +"' and origin_user_id = '"+ studentId+"';";
		result = db.execute(sql);
		db.close();
		return result;
	}
	
	//通过ownner找到借书者id
	public int findReceiveId(int bookId, int studentId) throws SQLException{
		int result = 0;
		String sql = "select receive_user_id from "+ tab +" where origin_user_id = '"
				+ studentId +"' and book_id = '"+ bookId +"'; ";
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		rs = db.executeQuery(sql);
		if(rs.next()){
			result = rs.getInt("receive_user_id");
		}
		db.close();
		return result;
	}
}
