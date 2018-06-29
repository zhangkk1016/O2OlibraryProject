package cn.hnust.book.model;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.dao.Transaction;

public class MessageListService {
	public JSONObject queryList(int studentId) throws SQLException, JSONException{
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		Transaction transaction = new Transaction();
		array = transaction.queryList(studentId);
		if(array != null){
			result.put("result", "Success");
			result.put("bookInformation", array);
		}
		else{
			result.put("result", "Wrong");
		}
		return result;
	}
}
