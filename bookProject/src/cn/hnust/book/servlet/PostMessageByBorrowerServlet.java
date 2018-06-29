package cn.hnust.book.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.book.model.PostMessageByBorrowerService;

public class PostMessageByBorrowerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PostMessageByBorrowerServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}
		reader.close();
		JSONObject object = new JSONObject();
		JSONObject obj = new JSONObject();
		int startUserId = 0;
		int receiveUserId = 0;
		int bookId = 0;
		String studentPhone = null;
		String message = null;
		System.out.println(buffer.toString());

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			object = new JSONObject(buffer.toString());
			startUserId = object.getInt("startUserId");
			receiveUserId = object.getInt("receiveUserId");
			bookId = object.getInt("bookId");
			studentPhone = object.getString("studentPhone");
			message = object.getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		PostMessageByBorrowerService postByBorrower = new PostMessageByBorrowerService();//引入service
		boolean flag = postByBorrower.postMessage(startUserId, receiveUserId, bookId, studentPhone, message);//逻辑处理
		try {
			if(flag){
				obj.put("result", "Success");			
			}else{
				obj.put("result", "Wrong");
			}
			out.print(obj.toString());
			out.flush();//刷新缓存
			out.close();//关闭out对象
		} catch (JSONException e) {
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
