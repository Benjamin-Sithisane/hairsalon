package com.login.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginDao {
	
	String sql = "select * from login where username=? and password=?";
	String url = "jdbc:mysql://localhost:3306/mylash?autoReconnect=true&useSSL=false";
	String userName = "root";
	String Password = "admin";
	
	public boolean check(String username, String password) {
		
		//try block for login
		try {
			//class driver
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection myConn = DriverManager.getConnection(url, userName, Password);
			
			PreparedStatement st = myConn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next())
			{
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
}
