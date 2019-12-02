package com.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.payment.Invoice;

public class AppointmentDao {
	
	private DataSource dataSource;
	
	public AppointmentDao(DataSource theDataSource) {
		dataSource = theDataSource;
	}

		public List<Appointment> getAppointment() throws Exception {
			
			List<Appointment> appointments = new ArrayList<>();
			
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			try {
				// get a connection
				myConn = dataSource.getConnection();
				
				// create sql statement
				String sql = "select * from appointment";
				
				myStmt = myConn.createStatement();
				
				// execute query
				myRs = myStmt.executeQuery(sql);
				
				// process result set
				while (myRs.next()) {
					
					// retrieve data from result set row
					int id = myRs.getInt("appointmentid");
					int customerid = myRs.getInt("customerid");
					String appointmentdate = myRs.getString("appointmentdate");
					String appointmenttime = myRs.getString("appointmenttime");
					String appointmenttype = myRs.getString("appointmenttype");
					Boolean appointmentcomplete = myRs.getBoolean("appointmentcomplete");
					
					// create new student object
					Appointment appointment = new Appointment(id, customerid, appointmentdate, appointmenttime, appointmenttype, appointmentcomplete);
					
					// add it to the list of students
					appointments.add(appointment);				
				}
				
				return appointments;		
			}
			finally {
				// close JDBC objects
				close(myConn, myStmt, myRs);
			}		
		}
		
		public void addAppointment(Appointment anAppointment) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create sql for insert
				String sql = "insert into appointment "
						   + "(appointmentdate, appointmenttime, appointmenttype) "
						   + "values (?, ?, ?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				// set the param values for the student
				myStmt.setString(1, anAppointment.getAppointmentdate());
				myStmt.setString(2, anAppointment.getAppointmenttime());
				myStmt.setString(3, anAppointment.getAppointmenttype());
				
				// execute sql insert
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
		}

		public Appointment getAppointment(String theAppointmentId) throws Exception {

			Appointment anAppointment = null;
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int appointmentId;
			
			try {
				// convert student id to int
				appointmentId = Integer.parseInt(theAppointmentId);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to get selected student
				String sql = "select * from customer where id=?";
				
				// create prepared statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, appointmentId);
				
				// execute statement
				myRs = myStmt.executeQuery();
				
				// retrieve data from result set row
				if (myRs.next()) {
					
					int customerid = myRs.getInt("customerid");
					String appointmentdate = myRs.getString("appointmentdate");
					String appointmenttime = myRs.getString("appointmenttime");
					String appointmenttype = myRs.getString("appointmenttype");
					Boolean appointmentcomplete = myRs.getBoolean("appointmentcomplete");
					
					// use the studentId during construction
					anAppointment = new Appointment(appointmentId, customerid, appointmentdate, appointmenttime, appointmenttype, appointmentcomplete);
				}
				else {
					throw new Exception("Could not find Appointment ID: " + appointmentId);
				}				
				
				return anAppointment;
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, myRs);
			}
		}

		public void updateAppointment(Appointment anAppointment) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;

			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create SQL update statement
				String sql = "update appointment "
							+ "set appointmentdate=?, appointmenttime=?, appointmenttype=?"
							+ "where id=?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setString(1, anAppointment.getAppointmentdate());
				myStmt.setString(2, anAppointment.getAppointmenttime());
				myStmt.setString(3, anAppointment.getAppointmenttype());
				// execute SQL statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
		}

		public void deleteAppointment(String anAppointmentId) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// convert student id to int
				int appointmentId = Integer.parseInt(anAppointmentId);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to delete student
				String sql = "delete from customer where id=?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, appointmentId);
				
				// execute sql statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC code
				close(myConn, myStmt, null);
			}	
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
