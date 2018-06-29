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

import cn.hnust.book.model.SearchBookService;

public class SearchBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SearchBookServlet() {
		super();
	}

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
			throws ServletException, IOException {

		response.setContentType("text/plain;charset=utf-8");

		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}
		reader.close();
		JSONObject object = new JSONObject();
		int studentId = 0;
		int bookId = 0;
		JSONArray array;
		JSONObject obj = new JSONObject();
		System.out.println(buffer.toString());

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			object = new JSONObject(buffer.toString());
			studentId = object.getInt("studentId");
			bookId = object.getInt("bookId");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SearchBookService searchBookService = new SearchBookService();
		try {
			array = searchBookService.queryBook(bookId, studentId);
			System.out.println(array.length());
			System.out.println("array:0"+ array +",array.length"+ array.length());
			if(array.length() > 0 ){
			obj.put("result", "Success");
			obj.put("bookInformation", array);
			}
			else{
				obj.put("result", "Wrong");
			}			
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
