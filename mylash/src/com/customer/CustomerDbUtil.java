package com.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CustomerDbUtil {
	private DataSource dataSource;
	
	public CustomerDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Customer> getCustomers() throws Exception {
		
		List<Customer> customers = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from customer";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("customerid");
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String address = myRs.getString("address");
				String city = myRs.getString("city");
				String zipcode = myRs.getString("zipcode");
				String state = myRs.getString("state");
				String phonenumber = myRs.getString("phonenumber");
				String username = myRs.getString("username");
				String email = myRs.getString("email");
				
				// create new student object
				Customer tempCustomer = new Customer(id, firstName, lastName, address, city, zipcode, state, phonenumber, username, email);
				
				// add it to the list of students
				customers.add(tempCustomer);				
			}
			
			return customers;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
	
	public void addCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into customer "
					   + "(firstname, lastname, address, city, zipcode, state, phonenumber, username, email) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theCustomer.getFirstname());
			myStmt.setString(2, theCustomer.getLastname());
			myStmt.setString(3, theCustomer.getAddress());
			myStmt.setString(4, theCustomer.getCity());
			myStmt.setString(5, theCustomer.getZipcode());
			myStmt.setString(6, theCustomer.getState());
			myStmt.setString(7, theCustomer.getPhonenumber());
			myStmt.setString(8, theCustomer.getUsername());
			myStmt.setString(9, theCustomer.getEmail());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Customer getCustomer(String theCustomerId) throws Exception {

		Customer theCustomer = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int customerId;
		
		try {
			// convert student id to int
			customerId = Integer.parseInt(theCustomerId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from customer where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, customerId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				
				String firstName = myRs.getString("firstname");
				String lastName = myRs.getString("lastname");
				String address = myRs.getString("address");
				String city = myRs.getString("city");
				String zipcode = myRs.getString("zipcode");
				String state = myRs.getString("state");
				String phonenumber = myRs.getString("phonenumber");
				String username = myRs.getString("username");
				String email = myRs.getString("email");
				
				// use the studentId during construction
				theCustomer = new Customer(customerId, firstName, lastName, address, city, zipcode, state, phonenumber, username, email);
			}
			else {
				throw new Exception("Could not find student id: " + customerId);
			}				
			
			return theCustomer;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateCustomer(Customer theCustomer) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update customer "
						+ "set firstname=?, lastname=?, address=?, city=?, zipcode= ?, state=?, phonenumber=?, username=?, email=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theCustomer.getFirstname());
			myStmt.setString(2, theCustomer.getLastname());
			myStmt.setString(3, theCustomer.getAddress());
			myStmt.setString(4, theCustomer.getCity());
			myStmt.setString(5, theCustomer.getZipcode());
			myStmt.setString(6, theCustomer.getState());
			myStmt.setString(7, theCustomer.getPhonenumber());
			myStmt.setString(8, theCustomer.getUsername());
			myStmt.setString(9, theCustomer.getEmail());
			myStmt.setInt(10, theCustomer.getCustomerid());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteCustomer(String theCustomerId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int customerId = Integer.parseInt(theCustomerId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from customer where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, customerId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
