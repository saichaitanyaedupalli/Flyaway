package com.flyaway;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/searchresults")
public class SearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html><text>");
		
		String src = request.getParameter("source");
		String des = request.getParameter("destination");
		String date = request.getParameter("date");
		String month = request.getParameter("month");
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
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM list_of_flights WHERE source = '"+src+"' AND destination = '"+des+"' and date = '"+date+"' and month = '"+month+"'");
			out.println("Avilable flights<br><br>");
			out.println("<table border=2><th>Flight Number<th>Source<th>Destination<th>Price</th>");
			while(rs.next()) {
				String flightNumber = rs.getString("FlightNumber");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				Float price = rs.getFloat("price");
								
				out.println("<tr><td>" + flightNumber + "</td><td>" + source + "</td><td>" + destination + "</td><td>" + price + "</td><td><a href=\"passengerdetails.jsp?flight_number=" + flightNumber + "&price=" + price + "\">Book</a></td></tr>");
				
			}
			
			out.println("</table>");
			
		}catch(Exception e) {
			out.println(e);
		}
    }
}