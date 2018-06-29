package cn.hnust.book.model;

import cn.hnust.book.dao.Login;

public class LoginService {
	public int login(int studentId, String studentName){
		int result  = -1;
		Login login = new Login();
		result = login.login(studentId, studentName);
		return result;
	}
}
