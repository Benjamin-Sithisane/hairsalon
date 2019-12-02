<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice</title>
</head>
<body>
	<table>
		<tr>
			<th>Appointment Number</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Appointment Date</th>
			<th>Appointment Type</th>
			<th>Payment Amount</th>
			<th>Invoice Number</th>
		</tr>
				
		<tr>
			<td>${INVOICE.appointmentid}</td>
			<td>${INVOICE.firstname}</td>
			<td>${INVOICE.lastname}</td>
			<td>${INVOICE.appointmentdate}</td>
			<td>${INVOICE.appointmenttype}</td>
			<td>${INVOICE.paymentamount}</td>
			<td>${INVOICE.invoicenum}</td>
		</tr>
		

	
	</table>
	
</body>
</html>