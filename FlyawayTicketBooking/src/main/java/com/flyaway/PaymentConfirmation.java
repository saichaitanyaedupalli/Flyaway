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
import java.sql.PreparedStatement;
import java.util.Properties;

@WebServlet("/cnfpay")
public class PaymentConfirmation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String unpaidAmount = request.getParameter("unpaidAmount");

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

            PreparedStatement stmt = connection.prepareStatement("UPDATE passengers SET paid = 'Y' WHERE paid = 'N'");
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                response.getWriter().println("Payment successful. " + rowsUpdated + " booking(s) updated.");
            } else {
                response.getWriter().println("No bookings were updated. Payment might not have been successful.");
            }

        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
            response.getWriter().println("An error occurred during payment processing.");
        }
    }
}
