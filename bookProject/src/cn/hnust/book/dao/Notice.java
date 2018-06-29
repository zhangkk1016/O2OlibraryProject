package cn.hnust.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.util.DBConnection;
import cn.hnust.book.util.DateTimeInServer;

public class Notice {
	
	String tab = "notices_information";
	
	//插入通知信息
	public int InsertMessage(int bookId,int startUserId,int receiveUserId, String studentPhone,String message){
		int flag = 0;
		//插入通知信息
		DBConnection db = new DBConnection();
		
		String time = new DateTimeInServer().getDateTime();
        
		String sql = "insert into "+ tab +"(book_id, start_user_id, receive_user_id, student_phone," +
				" message, time) values ('"+bookId+"','"+startUserId+"', '"+receiveUserId+"', '" +
						""+ studentPhone +"', '"+ message +"', '"+ time +"');";
		System.out.println(sql);
		flag = db.execute(sql);
		db.close();
		return flag;
	}
	
	//查询通知信息；flag：查询者身份
	public JSONObject query(int flag,int studentId, int bookId) throws JSONException{
		JSONObject result = new JSONObject();
		DBConnection db = new DBConnection();
		String sql1 = "select start_user_id userId, student_phone, message, time from "
		+ tab +" where receive_user_id = "+ studentId +" and book_id = "+ bookId +";";
		String sql2 = "select start_user_id userId, student_phone, message, time from "
				+ tab +" where start_user_id = "+ studentId +" and book_id = "+ bookId +";";
		ResultSet rs =  null;
		try {
			if(flag == 0){
				rs = db.executeQuery(sql2);
				System.out.println(sql2);
			}
			else{
				rs = db.executeQuery(sql1);
				System.out.println(sql1);
			}
			
			if(rs.next()){
				System.out.println(rs.getString("student_phone"));
				result.put("userId", rs.getInt("userId"));
				result.put("studentPhone",rs.getString("student_phone") );
				result.put("message", rs.getString("message"));
				result.put("time", rs.getDate("time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return result;
	}
}
