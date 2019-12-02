package com.payment;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Invoice")
public class InvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InvoiceDao invoiceDao;
	
	@Resource(name= "jdbc/mylash")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		try {
			invoiceDao = new InvoiceDao(dataSource);
		} 
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String appointmentId = request.getParameter("appoinmentid");
			
			Invoice anAppointment = invoiceDao.createInvoice(appointmentId);
			
			request.setAttribute("INVOICE", anAppointment);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/invoice.jsp");
			
			dispatcher.forward(request, response);
			
		} catch(Exception exc) {
			
			throw new ServletException(exc);
			
		}
	}
}
