package cn.hnust.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.util.DBConnection;

public class MyList {
	DBConnection db = null;
	String tab = "book_information";
	
	//根据userId查询个人书籍列表
	public JSONArray query(int studentId) throws JSONException{
		db = new DBConnection();
		JSONArray result = new JSONArray();	//使用JSONArray返回参数
		ResultSet rs = null;
		String sql = "select book_id, book_name, status from "+ tab +" where user_id = '"+studentId+"';";
		try {
			rs = db.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				JSONObject obj = new JSONObject();	//使用JSONObject接收参数
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int status = rs.getInt("status");
				obj.put("bookId",bookId);
				obj.put("bookName", bookName);
				obj.put("status", status);
				result.put(obj);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return result;
	}
	
	//根据studentId查询市场书籍
	public JSONArray queryBookById(int studentId) throws JSONException{
		JSONArray  array = new JSONArray();
		ResultSet rs = null;
		int resultNum = 0;
		DBConnection conn = new DBConnection();
		String sql = "select  book_id, book_name, status from "+ tab +" where user_id = "+ studentId +" and status = 3;";
		try {
			rs = conn.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				resultNum++;
				JSONObject obj = new JSONObject();
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int status = rs.getInt("status");
				obj.put("bookId",bookId);
				obj.put("bookName", bookName);
				obj.put("status", status);
				obj.put("resultNum", resultNum);
				array.put(obj);
				System.out.println("result= "+ resultNum);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return array;
	}
	
	//根据bookId查询市场书籍
	public JSONArray queryBook(int bookId) throws JSONException{
		JSONArray  array = new JSONArray();
		ResultSet rs = null;
		int resultNum = 0;
		DBConnection conn = new DBConnection();
		String sql = "select book_id, user_id, book_name, status, longitude, latitude, address from "+ tab +" where book_id = "+ bookId +" and status > 1;";
		try {
			rs = conn.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				resultNum++;
				JSONObject obj = new JSONObject();
				String bookName = rs.getString("book_name");
				int status = rs.getInt("status");
				int userId = rs.getInt("user_id");
				double longitude = rs.getDouble("longitude");
				double latitude = rs.getDouble("latitude");
				String address = rs.getString("address");
				int bookId1 = rs.getInt("book_id");
				obj.put("bookName", bookName);
				obj.put("status", status);
				obj.put("longitude", longitude);
				obj.put("latitude", latitude);
				obj.put("userId", userId);
				obj.put("resultNum", resultNum);
				obj.put("address", address);
				obj.put("bookId", bookId1);
				array.put(obj);
				System.out.println(array);
				System.out.println("result= "+ resultNum);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return array;
	}
	
	//查询所有书籍
	public JSONArray queryAllBook() throws JSONException{
		JSONArray array = new JSONArray();
		ResultSet rs = null;
		int resultNum = 0;
		DBConnection conn = new DBConnection();
		String sql = "select book_id, user_id, book_name, status, longitude, latitude, address from "+ tab +" where  status > 1;";
		rs = conn.executeQuery(sql);
		try {
			while(rs.next()) {
				resultNum++;
				JSONObject obj = new JSONObject();
				String bookName = rs.getString("book_name");
				int status = rs.getInt("status");
				int userId = rs.getInt("user_id");
				double longitude = rs.getDouble("longitude");
				double latitude = rs.getDouble("latitude");
				String address = rs.getString("address");
				int bookId1 = rs.getInt("book_id");
				obj.put("bookName", bookName);
				obj.put("status", status);
				obj.put("longitude", longitude);
				obj.put("latitude", latitude);
				obj.put("userId", userId);
				obj.put("resultNum", resultNum);
				obj.put("address", address);
				obj.put("bookId", bookId1);
				array.put(obj);
				System.out.println(array);
				System.out.println("result= "+ resultNum);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return array;
	}
	
	//根据userId查询位置坐标
	public JSONObject queryCoordinate(int userId) throws JSONException{
		JSONObject obj = new JSONObject();
		ResultSet rs = null;
		DBConnection conn = new DBConnection();
		String sql = "select longitude, latitude, address from "+ tab +" where user_id = '"+ userId +"';";
		try {
			rs = conn.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(rs.next()) {
				double longitude = rs.getDouble("longitude");
				double latitude = rs.getDouble("latitude");
				String address =  rs.getString("address");
				obj.put("longitude0", longitude);
				obj.put("latitude0", latitude);
				obj.put("address", address);
				System.out.println(obj);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return obj;
	}
	
	
	//更新书籍状态
	public boolean updateStatus(int bookId, int status){
		
		boolean flag = false;
		//发布图书
		db = new DBConnection();
		String sql = "update "+ tab +" set status = '"+ status +"' where book_id = '"+ bookId +"';";
		int temp = db.execute(sql);
		if(temp > 0){
			flag = true;
		}
		db.close();
		return flag;
	}
	
	//根据userId插入位置坐标
	public boolean insertCoordinate(double longitude,double latitude, int studentId, String address){
		DBConnection con = new DBConnection();
		boolean flag = false;
		String sql = "update "+ tab +" set longitude = '"+ longitude +"',latitude = '"+ latitude +"', address = '"+ address +"' where user_id = "+ studentId +";";
		int result = con.execute(sql);
		if(result > 0){
			flag = true;
		}
		con.close();
		return flag;
	}
	
	
	//更新拥有者即将拥有者从源拥有者变为借书者
	public int updateOwnner(int bookId, int receiveId){
		int result = -1;
		DBConnection con = new DBConnection();
		String sql = "update "+ tab +" set user_id = "+ receiveId +", status = 1 where book_id = "
				+ bookId +" order by id desc limit 1;";
		result = con.execute(sql);
		con.close();
		return result;
	}
}
