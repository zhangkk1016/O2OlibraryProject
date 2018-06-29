package cn.hnust.book.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.util.DBConnection;

public class BookLibrary {
	String tab = "book_information";
	//图书馆id-001
	int userId = 1;
	
	//借书
	public boolean borrow(int studentId, int bookId){
		int flag = -1;
		boolean result = false;
		MyList myList = new MyList();
		flag = myList.updateOwnner(bookId, studentId);
		if(flag > 0){
			result = true;
		}
		return result;
	}
	
	//还书
	public boolean giveBack(int bookId){
		boolean result = false;
		DBConnection con = new DBConnection();
		String sql = "update "+ tab +" set user_id = "+ userId +", status = 0 where book_id = "
				+ bookId +";";
		int flag = con.execute(sql);
		con.close();
		if (flag > 0){
			result = true;
		}
		return result;
	}
	
	//查询
	public JSONArray query(int bookId) throws JSONException{
		JSONArray result = new JSONArray();
		DBConnection conn = new DBConnection();
		ResultSet rs = null;
		String sql = "select  book_name, book_id from "+
		tab +" where book_id = "+ bookId +" and status = 0";
		rs = conn.executeQuery(sql);
		try {
			while(rs.next()){
				String bookName = rs.getString("book_name");
				JSONObject obj = new JSONObject();
				obj.put("bookName", bookName);
				obj.put("bookId", bookId);
				result.put(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//更新拥有者即将拥有者从源拥有者变为借书者
	public boolean updateOwnner(int bookId,int receiveId, double longitude,	double latitude, String address){
		boolean result = false;
		int flag = 0;
		DBConnection db = new DBConnection();
		Connection con = db.getConnection();
		try {
			con.setAutoCommit(false);//关闭数据库连接的自动提交功能
			
			String sql1 = "update "+ tab +" set user_id = '"+ userId +"'where book_id = '"
					+ bookId +"';";
			flag += db.execute(sql1);
			
	        String sql2 = "update "+ tab +" set longitude = '"+ longitude +"', latitude = '"+ latitude +"', " +
	        		"address = '"+address +"', user_id = '"+ receiveId +"' where book_id = "+ bookId +";";
	        flag += db.execute(sql2);
	        
	        
	        con.commit();//如果事务执行完毕，手动提交。
	        System.out.println("commit fine!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//事务过程中，发生异常，数据回滚。
		}finally{
			if(flag == 2){
				result = true;
			}
			 try {
	            //将数据库连接交还给连接池时，必须要重新开启事务的自动提交功能
	            con.setAutoCommit(true);
				con.close();
				db.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
