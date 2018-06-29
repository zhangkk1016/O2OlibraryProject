package cn.hnust.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.util.DBConnection;


public class Login {
	
	
	DBConnection db = null;
	String tab = "personal_information";
	public int login(int studentId, String studentName){
		int result = -1;
		db = new DBConnection();	//�������ݿ⹤����
		ResultSet rs = null;	//�����
		String sql = "select student_id id from "+ tab +" where student_id = '"+studentId+"' and " +
				"student_name = '"+studentName+"';";	//��ѯ���������Ƿ���ȷ
		try {
			rs = db.executeQuery(sql);	//ִ��SQL
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				return rs.getInt("id");	//�����򷵻�ѧ��id
			} else
				return -1;	//�������򷵻�-1
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return result;
		
		
	}
	public String queryById(int studentId){
		String name = null;
		db = new DBConnection();
		ResultSet rs = null;
		String sql = "select student_name name from "+ tab +" where student_id = "+studentId+";";
		rs = db.executeQuery(sql);
		try {
			if(rs.next()){
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return name;
	}
	
	//ͨ��friendId��ѯ��Ϣ
	public JSONObject query(int studentId) throws SQLException, JSONException{
		JSONObject obj = new JSONObject();
		db = new DBConnection();
		ResultSet rs = null;
		String sql = "select student_name, 	student_id, student_phone from "+ tab +" where student_id = "+ studentId +";";
		
		rs = db.executeQuery(sql);
		
		if(rs.next()){
			obj.put("result", "Success");
			obj.put("studentName", rs.getString("student_name"));
			obj.put("studentId", rs.getInt("student_id"));
			obj.put("studentPhone", rs.getString("student_phone"));
		}
		else{
			obj.put("result", "Wrong");
		}
		db.close();
		return obj;
	}
}
