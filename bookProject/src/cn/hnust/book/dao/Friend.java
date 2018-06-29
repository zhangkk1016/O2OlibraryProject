package cn.hnust.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.util.DBConnection;

public class Friend {
	
	String tab = "friend";
	DBConnection db;
	
	//≤È—Ø≈Û”—Id
	public ArrayList<Integer> queryFriend(int studentId) throws SQLException{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		ResultSet rs1;
		ResultSet rs2;
		DBConnection db2 = new DBConnection();
		db = new DBConnection();
		String allow = "y";
		String sql1 = "select friend_A_Id from "+ tab +" where friend_B_Id = "+ studentId +" and allow = '"+ allow +"';";
		String sql2 = "select friend_B_Id from "+ tab +" where friend_A_Id = "+ studentId +" and allow = '"+ allow +"';";
		rs1 = db.executeQuery(sql1);
		rs2 = db2.executeQuery(sql2);
		
		while(rs1.next()){
			int result = rs1.getInt("friend_A_Id");
			list.add(result);
		}
		while(rs2.next()){
			int result = rs2.getInt("friend_B_Id");
			list.add(result);
		}
		
		db.close();
		db2.close();
		
		return list;
	}
	
	//≤È—Ø≈Û”—id∫Õ–’√˚
	public JSONArray query(int studentId) throws SQLException, JSONException{
		
		JSONArray result = new JSONArray();
		
		db = new DBConnection();
		DBConnection db2 = new DBConnection();
		
		ResultSet rs1;
		ResultSet rs2;
		
		String allow = "y";
		String sql1 = "select friend_A_Id, friend_A_name from "+ tab +" where friend_B_Id = "+ studentId +" and allow = '"+ allow +"';";
		String sql2 = "select friend_B_Id, friend_B_name from "+ tab +" where friend_A_Id = "+ studentId +" and allow = '"+ allow +"';";
				
		rs1 = db.executeQuery(sql1);
		rs2 = db2.executeQuery(sql2);
		
		while(rs1.next()){
			JSONObject obj = new JSONObject();
			int friendId = rs1.getInt("friend_A_Id");
			String friendName = rs1.getString("friend_A_name");
			obj.put("friendId", friendId);
			obj.put("friendName", friendName);
			result.put(obj);			
		}
		while(rs2.next()){
			JSONObject obj = new JSONObject();
			int friendId = rs2.getInt("friend_B_Id");
			String friendName = rs2.getString("friend_B_name");
			obj.put("friendId", friendId);
			obj.put("friendName", friendName);
			result.put(obj);
		}
		
		db.close();
		db2.close();
		
		return result;
	}
	
	//≤È—Ø…Í«Î¡–±Ì
	public JSONArray queryApplyList(int studentId) throws SQLException, JSONException{
		JSONArray result = new JSONArray();
		
		db = new DBConnection();
		ResultSet rs = null;
		String allow = "apply";
		String sql = "select friend_A_Id, friend_A_name from "+ tab +" where friend_B_Id = "+ studentId +" and allow = '"+ allow +"';";
		
		rs = db.executeQuery(sql);
		
		while(rs.next()){
			JSONObject obj = new JSONObject();
			int friendId = rs.getInt("friend_A_Id");
			String friendName = rs.getString("friend_A_name");
			obj.put("friendId", friendId);
			obj.put("friendName", friendName);
			result.put(obj);
		}
		db.close();
		return result;
	}
	//ÃÌº”≈Û”—
	public boolean apply(int studentId,String studentName, int friendId, String friendName){
		boolean result = false;
		String allow = "apply";
		db = new DBConnection();
		String sql = "insert into "+ tab +"(friend_A_id, friend_A_name, friend_B_id, friend_B_name, allow) values("
		+studentId+",'"+ studentName +"',"+ friendId +",'"+ friendName +"','"+ allow +"')";
		int flag = db.execute(sql);
		if(flag == 1){
			result = true;
		}
		db.close();
		return result;
	}
	
	//Õ¨“‚/æ‹æ¯ÃÌº”≈Û”—
	public boolean allow(int studentId, int friendId, String friendAllow){
		
		boolean result = false;
		//Õ¨“‚/æ‹æ¯ÃÌº”≈Û”—
		db = new DBConnection();
		
		String sql = "update "+ tab +" set allow = '"+ friendAllow +"' where friend_A_id = "+ friendId +" and " +
				"friend_B_id = "+ studentId +";";
		
		int flag = db.execute(sql);
		
		if(flag > 0){
			result = true;
		}
		return result;
	}
	
	//…æ≥˝∫√”—
	public int deleteFriend(int studentId, int friendId){
		int result = -1;
		db = new DBConnection();
		DBConnection con = new DBConnection();
		
		String sql1 = "delete from "+ tab +" where friend_A_Id = "+ studentId +" and friend_B_Id = "+ friendId +";";
		String sql2 = "delete from "+ tab +" where friend_B_Id = "+ studentId +" and friend_A_Id = "+ friendId +";";
		
		int flag1 = db.execute(sql1);
		int flag2 = con.execute(sql2);
		
		result = flag1 + flag2;
		
		db.close();
		con.close();
		return result;
	}
}
