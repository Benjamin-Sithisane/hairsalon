<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment</title>
</head>
<body>
<table>
	<tr>
		<th>Appointment No</th>
		<th>Appointment Date</th>
		<th>Appointment Time</th>
	
	</tr>
	<c:forEach var="appointment" items="${APPOINTMENT_LIST}">
		
		<c:url var="tempLink" value="AppointmentController">
			<c:param name="command" value="LOAD" />
			<c:param name="appointmentid" value="${appointment.appointmentid}" />
		</c:url>
		
		<c:url var="detailsLink" value="AppointmentController">
			<c:param name="command" value="DETAIL" />
			<c:param name="appointmentid" value="${appointment.appointmentid}" />
		</c:url>
		
		<c:url var="deleteLink" value="AppointmentController">
			<c:param name="command" value="DELETE" />
			<c:param name="appointmentid" value="${appointment.appointmentid}" />
		</c:url>
		
		
		
		<tr>
			<td>${appointment.appointmentid}</td>
			<td>${appointment.appointmentdate}</td>
			<td>${appointment.appointmenttime}</td>
			<td>
				<a href="${tempLink}">Update</a> 
							 |
				<a href="${detailsLink}">Details</a>
							 |
				<a href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
				Delete</a>	
			</td>
		</tr>		
	</c:forEach>
	</table>
</body>
</html>