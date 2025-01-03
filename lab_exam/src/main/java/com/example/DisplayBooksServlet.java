package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DisplayBooksServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Set the response type to text/html
        response.setContentType("text/html");
        
        try {
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "querywork");
            Statement statement = connection.createStatement();
            
            // Switch to bookstore database
            String useDatabaseSQL = "USE BookstoreDB";
            statement.executeUpdate(useDatabaseSQL);

            String selectSQL = "SELECT * FROM Books";
			ResultSet resultSet = statement.executeQuery(selectSQL);
                
            // Close the connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>An error occurred: " + e.getMessage() + "</h2>");
        }
		
	}
}
