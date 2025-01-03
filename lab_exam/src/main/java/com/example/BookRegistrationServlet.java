package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@WebServlet("/registerBook")
public class BookRegistrationServlet extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set the response type to text/html
        response.setContentType("text/html");

        // Get task details from the request
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        System.out.println(title);

        // Establish a database connection and insert the book
//        try (DBConnectionManager dbManager = new DBConnectionManager()) {
//        dbManager.openConnection();
//        Connection connection = dbManager.getConnection();
        try{
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "querywork");
            Statement statement = connection.createStatement();
            
            // Switch to bookstore database
            String useDatabaseSQL = "USE BookstoreDB";
            statement.executeUpdate(useDatabaseSQL);

            // SQL to insert the book
            String sql = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.setDouble(3, Double.parseDouble(price));

                int rowsInserted = preparedStatement.executeUpdate();
                PrintWriter out = response.getWriter();

                if (rowsInserted > 0) {
                    out.println("<h2>Book successfully registered!</h2>");
                } else {
                    out.println("<h2>Failed to register the book. Please try again.</h2>");
                }
                
             // Close the connection
                statement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>An error occurred: " + e.getMessage() + "</h2>");
        }
    }
}
