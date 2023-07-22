package com.flyaway;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><text>");
		
		InputStream in = getServletContext().getResourceAsStream("WEB-INF/config.properties");
		Properties props = new Properties();
		props.load(in);
		String url = props.getProperty("url");
		String userid = props.getProperty("userid");
		String password = props.getProperty("password");
		FlyawayDBcon flyawaydbcon = null;
		try {
			flyawaydbcon = new FlyawayDBcon(url, userid, password);
			Connection connection = flyawaydbcon.getConnection();
			
			Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM passengers");
			out.println("Passengers<br><br>");
			out.println("<table border=2><th>First Name<th>Last Name<th>Gender</th>");
			while(rs1.next()) {

			String firstName = rs1.getString("First_Name");
			String lastName = rs1.getString("Last_Name");
			String gender = rs1.getString("gender");
			
			out.println("<tr><td>"+firstName+"</td><td>"+lastName+"</td><td>"+gender+"</td></tr>");
			}
			Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs2 = stmt2.executeQuery("SELECT SUM(price) FROM passengers WHERE paid = 'N'");
			
			if(rs2.next()) {
				int unpaid = rs2.getInt(1);
				
				out.print("<a href=\"payment.jsp?unpaid=" + unpaid + "\">click here to pay</a>");
//	            request.getRequestDispatcher(redirectURL).forward(request, response);
				
//				out.println("<a href=\"payment.jsp?unpaid=" + unpaid + "\"></a>");
				
//				out.println("<form action='/cnfpay' method='POST'>");
//				out.println("Booking Charges: <input type='text' name='unpaidAmount' value='" + unpaid + "' readonly='readonly'><br>");
//				out.println("<input type='submit' value='Pay'>");
//				out.println("</form>");
				
//				out.println("Booking Charges: <input type='text' name='unpaidAmount' value='" + unpaid + "' readonly='readonly'><br>");
//				out.println("<input type='submit' value='Pay'>");
			}
			
//			out.println("Your transaction is successful");
		}catch (Exception e) {
		System.out.println("Exception thrown : " + e);
		}
	}

	
}
