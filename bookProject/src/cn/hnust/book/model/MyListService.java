package cn.hnust.book.model;


import org.json.JSONArray;
import org.json.JSONException;

import cn.hnust.book.dao.MyList;

public class MyListService {
	public JSONArray queryList(int studentId) throws JSONException{
		MyList myList = new MyList();
		JSONArray result = new JSONArray();
		result = myList.query(studentId);
		return result;
	}
	
	//²åÈë×ø±ê
	public boolean insertCoordinate(double longitude,double latitude, int studentId, String address){
		boolean flag = false;
		MyList myList = new MyList();
		flag = myList.insertCoordinate(longitude, latitude, studentId, address);
		return flag;
	}
	
}
