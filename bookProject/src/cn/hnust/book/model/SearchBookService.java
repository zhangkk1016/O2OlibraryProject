package cn.hnust.book.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.dao.MyList;

public class SearchBookService {	
	private static double EARTH_RADIUS = 6378.137; 
	public JSONArray queryBook(int bookId, int userId) throws JSONException{
		
		JSONArray array1 = new JSONArray();
		JSONObject obj = new JSONObject();
		MyList list = new MyList();
		//搜索书籍信息
		array1 = list.queryBook(bookId);
		System.out.println("array1:"+array1);
		obj = list.queryCoordinate(userId);
		System.out.println("obj:" + obj);
		boolean flag = (obj !=  null); 
		System.out.println("flag"+flag);
		//自己的位置
		if(obj != null){
			double longitude0 = obj.getDouble("longitude0");
			double latitude0 = obj.getDouble("latitude0");
			double radLat1 = rad(latitude0);
			double radLng1 = rad(longitude0);
		
			int index = array1.length() - 1;
			System.out.println("index:"+index);
			while(index >= 0){
				double distance;
				//书籍拥有者的位置
				double radLat2 = rad(array1.getJSONObject(index).getDouble("latitude"));
				double radLng2 = rad(array1.getJSONObject(index).getDouble("longitude"));;
				double difference = radLat1 - radLat2;
				double mdifference = radLng1 - radLng2;
				distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
	                + Math.cos(radLat1) * Math.cos(radLat2)
	                * Math.pow(Math.sin(mdifference / 2), 2)));
				distance = distance * EARTH_RADIUS;
				distance = Math.round(distance * 10000) / 10000;
				array1.getJSONObject(index).put("distance", distance);	        
				index --;
			}
		}
		else{
			array1 = null; 
		}
		return array1;
	}
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
    public JSONObject queryAllBook() throws JSONException{
    	JSONObject result = new JSONObject();
    	MyList list = new MyList();
    	JSONArray array = list.queryAllBook();
    	if(array.length() > 0){
    		result.put("result", "Success");
    		result.put("bookInformation", array);
    	}
    	else{
    		result.put("result", "Wrong");
    	}
    	return result;
    }
}
