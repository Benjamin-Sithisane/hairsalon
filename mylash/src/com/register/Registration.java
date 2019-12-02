package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//SQL data
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		String url = "jdbc:mysql://localhost:3306/login?autoReconnect=true&useSSL=false";
		String usql = "root";
		String psql = "admin";
		
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		try {
			
			//Driver for SQL
			Class.forName("com.mysql.jdbc.Driver");
			
			//Establishes DB connection
			Connection myConn = DriverManager.getConnection(url, usql, psql);
			
			//SQL Statement
			PreparedStatement st = myConn.prepareStatement(
					"insert into userlogin values(?, ?)");
			
			//Set the values for st
			st.setString(1, u);
			st.setString(2, p);
			
			//Execute SQL statement
			int i = st.executeUpdate();
			
			if (i > 0) {
				out.print("Account Created");
			}
		
			
		} catch (Exception e) {
			out.close();
			
		}
		
	}

}
