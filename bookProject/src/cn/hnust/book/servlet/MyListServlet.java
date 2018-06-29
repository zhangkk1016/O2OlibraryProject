package cn.hnust.book.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.model.MyListService;

public class MyListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		response.setContentType("text/plain;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}
		reader.close();
		JSONObject object = new JSONObject();
		JSONArray array;
		int studentId = 0;
		double longitude = 0.00d;
		double latitude = 0.00d;
		String address = null;
		boolean flag = true;
		System.out.println(buffer.toString());
		try {
			object = new JSONObject(buffer.toString());
			studentId = object.getInt("studentId");
			longitude = object.getDouble("longitude");
			latitude = object.getDouble("latitude");
			address = object.getString("address");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyListService myListService = new MyListService();
		try {
			//查找书籍列表
			array = myListService.queryList(studentId);
		
			//插入地理位置信息
			flag = myListService.insertCoordinate(longitude, latitude, studentId, address);
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			JSONObject obj = new JSONObject();
			if(array.length() > 0 && flag == true){
				obj.put("result", "Success");
				obj.put("bookInformation", array);
				
			}else{
				obj.put("result", "Success");
				obj.put("bookInformation", array);
			}	
			System.out.println("obj:"+obj);
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
