<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Passenger Details</title>
</head>
<body>
    <% String flightNumber = request.getParameter("flight_number"); %>
    <% String price = request.getParameter("price"); %>
    <form action="passengerdetails" method="POST">
        <input type="hidden" name="flightNumber" value="<%= flightNumber %>">
        <input type="hidden" name="price" value="<%= price %>">
    
        First name: <input type="text" name="firstName"><br><br>
        Last name: <input type="text" name="lastName"><br><br>
        Gender: 
        <input type="radio" name="userGender" value="male">Male
        <input type="radio" name="userGender" value="female">Female<br><br>
        Contact number: <input type="text" name="mobileNum"><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>