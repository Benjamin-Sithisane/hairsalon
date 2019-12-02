package com.appointment;

import java.util.List;

import java.io.IOException;
import javax.sql.DataSource;

import com.payment.Invoice;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppointmentController")
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AppointmentDao appointmentDao;
	
	@Resource(name= "jdbc/mylash")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		try {
			appointmentDao = new AppointmentDao(dataSource);
		} 
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST": 
				listAppointments(request, response);
				break;
			
			case "ADD": 
				addAppointments(request, response);
				break;
			
			case "DETAIL":
				detailAppointments(request, response);
				break;
				
			case "LOAD": 
				loadAppointments(request, response);
				break;
			
			case "UPDATE": 
				updateAppointments(request, response);
				break;
			
			case "DELETE": 
				deleteAppointments(request, response);
				break;
				
			default: 
				listAppointments(request, response);
			}
			
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	private void listAppointments (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Appointment> appointments = appointmentDao.getAppointment();
		
		request.setAttribute("APPOINTMENT_LIST", appointments);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/appointments.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addAppointments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String appointmentdate = request.getParameter("appointmentdate");
		String appointmenttime = request.getParameter("appointmenttime");
		String appointmenttype = request.getParameter("appointmenttype");
		
		Appointment anAppointment = new Appointment(appointmentdate, appointmenttime, appointmenttype);
		
		appointmentDao.addAppointment(anAppointment);
		
		listAppointments(request, response);
	}
	
	private void loadAppointments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String appointmentId = request.getParameter("appointmentid");
		
		Appointment anAppointment = appointmentDao.getAppointment(appointmentId);
		
		request.setAttribute("THE_APPOINTMENT", anAppointment);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-appointment.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void updateAppointments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String appointmentdate = request.getParameter("appointmentdate");
		String appointmenttime = request.getParameter("appointmenttime");
		String appointmenttype = request.getParameter("appointmenttype");
		
		Appointment appointment = new Appointment(appointmentdate, appointmenttime, appointmenttype);
		
		appointmentDao.updateAppointment(appointment);
		
		listAppointments(request, response);
	}
	
	private void deleteAppointments(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		String appointment = request.getParameter("appointmentid");
		
		appointmentDao.deleteAppointment(appointment);
		
		listAppointments(request, response);
	}
	
	private void detailAppointments(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		String appointmentId = request.getParameter("appoinmentid");
		
		Invoice anAppointment = appointmentDao.createInvoice(appointmentId);
		
		request.setAttribute("INVOICE", anAppointment);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/invoice.jsp");
		
		dispatcher.forward(request, response);
	}
}
	

