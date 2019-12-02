package com.customer;

import java.util.List;

import java.io.IOException;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.CustomerDbUtil;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CustomerDbUtil customerDbUtil;
	
	@Resource(name= "jdbc/mylash")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		try {
			customerDbUtil = new CustomerDbUtil(dataSource);
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
				listCustomers(request, response);
				break;
			
			case "ADD": 
				addCustomers(request, response);
				break;
			
			case "LOAD": 
				loadCustomers(request, response);
				break;
			
			case "UPDATE": 
				updateCustomers(request, response);
				break;
			
			case "DELETE": 
				deleteCustomers(request, response);
				break;
				
			default: 
				listCustomers(request, response);
			}
			
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Customer> customers = customerDbUtil.getCustomers();
		
		request.setAttribute("CUSTOMER_LIST", customers);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Test.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipcode");
		String state = request.getParameter("state");
		String phoneNumber = request.getParameter("phonenumber");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		
		Customer theCustomer = new Customer(firstName, lastName, address, city, zipCode, state, phoneNumber, userName, email);
		
		customerDbUtil.addCustomer(theCustomer);
		
		listCustomers(request, response);
	}
	
	private void loadCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String theCustomerId = request.getParameter("customerid");
		
		Customer theCustomer = customerDbUtil.getCustomer(theCustomerId);
		
		request.setAttribute("THE_CUSTOMER", theCustomer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-customer-form.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void updateCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("customerId"));
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipcode");
		String state = request.getParameter("state");
		String phoneNumber = request.getParameter("phonenumber");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		
		Customer theCustomer = new Customer(id, firstName, lastName, address, city, zipCode, state, phoneNumber, userName, email);
		
		customerDbUtil.updateCustomer(theCustomer);
		
		listCustomers(request, response);
	}
	
	private void deleteCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		String theCustomerId = request.getParameter("customerid");
		
		customerDbUtil.deleteCustomer(theCustomerId);
		
		listCustomers(request, response);
	}
}
