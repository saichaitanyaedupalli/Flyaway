//package com.flyaway;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import com.flyaway.SearchResults;
//
//public class FlightNumberServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		PrintWriter out = response.getWriter();
//		
//		ServletContext sc = getServletContext();
//    	RequestDispatcher rd = sc.getRequestDispatcher("searchresults");
//    	rd.include(request, response);
//    	String flightNumber;
//		try {
//			flightNumber = SearchResults.getFlightNumber();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        out.println(flightNumber);
//	}
//
//	
//}
