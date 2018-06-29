package cn.hnust.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
	
	
	private Connection connect = null;
	private Statement statement = null;
	String url = "jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf8";
	String user = "root";
	String psw = "";
	public DBConnection(){			
	 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(url,user,psw);		
			statement = connect.createStatement();
			System.out.println("connection ok!!!!!!!!!!!");
     } catch (SQLException e) {
         System.out.println("MySQLÁ¬½Ó´íÎó!!!!!!!!!!!");
         e.printStackTrace();
     } catch (Exception e) {
         e.printStackTrace();
     } 
	 
	 
	}
	
	public ResultSet executeQuery(String strSQL){
		ResultSet resultSet = null;
		try {
			System.out.println(strSQL);
			if(statement==null){
				System.out.println("statement is null!!");
			}
			else
				resultSet = statement.executeQuery(strSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public int execute(String strSQL){
		int row = -1;
		try {
			System.out.println(strSQL);
			 row = statement.executeUpdate(strSQL);
			System.out.println("row="+row);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public void close(){
		try {
		    if (statement != null) {
		        statement.close();
		    }
		    if (connect != null) {
		        connect.close();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return this.connect;
	}
}
