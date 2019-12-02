package com.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class InvoiceDao {
	private DataSource dataSource;
	
	public InvoiceDao(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public Invoice createInvoice(String InvoiceId) throws Exception {

		Invoice getInvoice = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int appointmentId;
		
		try {
			// convert student id to int
			appointmentId = Integer.parseInt(InvoiceId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student

			String sql = "select appointment.appointmentid, customer.firstname, customer.lastname, appointment.appointmentdate, appointment.appointmenttype, payment.paymentamount, payment.invoicenum "
					+ "from appointment join customer on appointment.customerid=customer.customerid "
					+ "join payment on appointment.appointmentid=payment.appointmentid"
					+ "where appointment.appointmentid = ?;";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, appointmentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstname = myRs.getString("firstname");
				String lastname = myRs.getString("lastname");
				String appointmentdate = myRs.getString("appointmentdate");
				String appointmenttype = myRs.getString("appointmenttype");
				float paymentamount = myRs.getFloat("paymentamount");
				String invoicenum = myRs.getString("invoicenum");
				
				// use the studentId during construction
				getInvoice = new Invoice(appointmentId, firstname, lastname, appointmentdate, appointmenttype, paymentamount, invoicenum);
			}
			else {
				throw new Exception("Could not find Appointment ID: " + appointmentId);
			}				
			
			return getInvoice;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
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
