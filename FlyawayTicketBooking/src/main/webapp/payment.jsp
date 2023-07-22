<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
<% String unpaid = request.getParameter("unpaid"); %>
<body>
<form action="cnfpay" method="POST">

Unpaid amount: <input type="text" name=unpaidAmount value="${unpaid}" readonly />
<input type="submit" value="Pay Now" />
</form>
</body>
</html>