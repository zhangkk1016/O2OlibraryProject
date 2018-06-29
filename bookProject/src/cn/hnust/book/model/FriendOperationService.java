package cn.hnust.book.model;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.dao.Friend;
import cn.hnust.book.dao.Login;
import cn.hnust.book.dao.MyList;

public class FriendOperationService {
	
	public JSONObject findFriendBookList(int studentId) throws SQLException, JSONException{
		
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		Friend friend = new Friend();
		ArrayList<Integer> list = friend.queryFriend(studentId);
		if(!list.isEmpty()){
			Login login = new Login();
			MyList myList = new MyList();
			for(int index = 0;index < list.toArray().length; index++){
				
				int friendId = list.get(index);	
				JSONArray array1 = myList.query(friendId);
				String friendName = login.queryById(friendId);
				System.out.println("array1"+array1);
				for(int i = 0; i < array1.length(); i++){
					JSONObject obj = new JSONObject();
					System.out.println("array1i"+array1.get(i));
					int bookId = ((JSONObject) array1.get(i)).getInt("bookId");
					String bookName = ((JSONObject) array1.get(i)).getString("bookName");
					int status = ((JSONObject) array1.get(i)).getInt("status");
					obj.put("bookId", bookId);
					obj.put("bookName", bookName);
					obj.put("status", status);
					obj.put("friendName",friendName);
					obj.put("friendId",friendId);
					array.put(obj);
					System.out.println("array:"+array);
					System.out.println(i);
				}
				
			}
			System.out.println("array:"+array);
			if(array.length() > 0){
				result.put("result", "Success");
				result.put("information", array);
			}
			else{
				result.put("result", "Wrong");
			}
		}
		else{
			result.put("result", "Wrong");
		}
		
		return result;
	}
	
	public JSONObject findFriendReleaseBookList(int studentId) throws SQLException, JSONException{
		
		JSONObject result =  new JSONObject();
		JSONArray array = new JSONArray();
		Friend friend = new Friend();
		ArrayList<Integer> list = friend.queryFriend(studentId);
		Login login = new Login();
		MyList myList = new MyList();
		if(!list.isEmpty()){
			for(int index = 0;index < list.toArray().length; index++){
				
				int friendId = list.get(index);	
				JSONArray array1 = myList.queryBookById(friendId);
				String friendName = login.queryById(friendId);
				System.out.println("array1: "+ array1);
					for(int i = 0;i < array1.length(); i++){
						JSONObject obj = new JSONObject();
						int bookId = ((JSONObject) array1.get(i)).getInt("bookId");
						String bookName = ((JSONObject) array1.get(i)).getString("bookName");
						int status = ((JSONObject) array1.get(i)).getInt("status");
						obj.put("bookId", bookId);
						obj.put("bookName", bookName);
						obj.put("status", status);
						obj.put("friendName",friendName);
						obj.put("friendId",friendId);
						array.put(obj);
						System.out.println("array:"+array);
					}
				}
			}
			if(array.length() == 0){
				System.out.println("array: "+array);
				result.put("result", "Success");				
			}
			else{
				result.put("result", "Success");
				result.put("information", array);
			}
			
		return result;
	}
	
	public JSONObject findFriendList(int studentId) throws SQLException, JSONException{
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		Friend friend = new Friend();
		array = friend.query(studentId);
		if(array != null){
			result.put("result", "Success");
			result.put("information", array);
		}
		else{
			result.put("result", "Wrong");
		}
		return result;
	}
	
	public boolean friendAdd(int studentId, int friendId){
		
		boolean result = false;
		
		Login login = new Login();
		Friend friend = new Friend();
		String studentName = login.queryById(studentId);
		String friendName = login.queryById(friendId);
		//ÅóÓÑÉêÇë
		if(studentName != null && friendName != null){
			result = friend.apply(studentId, studentName, friendId, friendName);
		}
		else{
			result = false;
		}
		return result;
	}
	
	public JSONArray findApplyList(int studentId) throws SQLException, JSONException{
		JSONArray result = new JSONArray();
		Friend friend = new Friend();
		//²éÑ¯ÉêÇëÁÐ±í
		result = friend.queryApplyList(studentId);
		return result;
	}
	
	public JSONObject friendAllow(int studentId, int friendId, String friendAllow) throws JSONException{
		JSONObject result = new JSONObject();
		Friend friend = new Friend();
		boolean flag = friend.allow(studentId, friendId, friendAllow);
		if(flag){
			result.put("result", "Success");
		}
		else{
			result.put("result", "Wrong");
		}
		return result;
	}
	
	public JSONObject friendInformation(int friendId) throws SQLException, JSONException{
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		Login login = new Login();
		MyList myList = new MyList();
		
		array = myList.query(friendId);
		result = login.query(friendId);
		
		if("Success".equals(result.getString("result"))){
			result.put("bookInformation", array);
		}
		return result;
	}
	
	public JSONObject deleteFriend(int studentId, int friendId) throws JSONException{
		JSONObject result = new JSONObject();
		Friend friend = new Friend();
		int temp = friend.deleteFriend(studentId, friendId);
		if(temp > 0){
			result.put("result", "Success");
		}
		else{
			result.put("result", "Wrong");
		}
		return result;
	}
	
	public JSONObject findFriend(int friendId) throws JSONException{
		
		JSONObject result = new JSONObject();
		Login login = new Login();
		
		String friendName = login.queryById(friendId);
		if("".equals(friendName) || friendName == null){
			result.put("result", "Wrong");
		}
		else{
			result.put("result", "Success");
			result.put("friendName", friendName);
			result.put("friendId", friendId);
		}
		
		return result;
	}
}
