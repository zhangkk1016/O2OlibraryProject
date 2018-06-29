package cn.hnust.book.model;

import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.dao.Notice;
import cn.hnust.book.dao.Transaction;

public class ObserveService {
	public JSONObject queryNotice(int studentId, int bookId, int flag) throws JSONException{
		
		JSONObject result = new JSONObject();
		JSONObject result1 = new JSONObject();
		JSONObject result2 = new JSONObject();
		Transaction transaction = new Transaction();
		result1 = transaction.queryStatus(bookId);
		Notice notice = new Notice();
		result2 = notice.query(flag, studentId, bookId);

		if(result1 != null && result2 != null){
			result.put("result", "Success");
			result.put("bookId", bookId);
			result.put("status",result1.getInt("status"));
			result.put("startUserId", result2.getInt("userId"));
			result.put("studentPhone",result2.getString("studentPhone") );
			result.put("message", result2.getString("message"));
			result.put("time", result2.get("time"));
		}
		else{
			result.put("result", "Wrong");
		}
		
		return result;
	}
}
