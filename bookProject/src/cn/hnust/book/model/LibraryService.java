package cn.hnust.book.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.dao.BookLibrary;

public class LibraryService {
	public JSONObject borrow(int bookId, int studentId) throws JSONException{
		JSONObject result = new JSONObject();
		BookLibrary library = new BookLibrary();
		boolean flag = library.borrow(studentId, bookId);
		if(flag){
			result.put("result", "Success");			
		}
		else{
			result.put("result", "Wrong");
		}
		return result;
	}
	
	public JSONObject giveBack(int bookId) throws JSONException{
		JSONObject result = new JSONObject();
		BookLibrary library = new BookLibrary();
		boolean flag = library.giveBack(bookId);
		if(flag){
			result.put("result", "Success");			
		}
		else{
			result.put("result", "Wrong");
		}
		return result;
	}
	
	public JSONObject queryBook(int bookId) throws JSONException{
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		BookLibrary library = new BookLibrary();
		array = library.query(bookId);
		System.out.println(array);
		if(array == null && "".equals(array)){
			result.put("result", "Wrong");
		}
		else{
			result.put("result", "Success");
			result.put("bookInformation",array);
		}
		return result;
	}
}
