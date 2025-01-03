package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@NoArgsConstructor
public class DBConnectionManager implements AutoCloseable {
	 String url = "jdbc:mysql://localhost:3306/"; // Base URL
     String user = "root";
     String password = "querywork";
     private Connection connection;

    // Open the connection
    public void openConnection() {
        try {
        	connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established successfully.");        	
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("1. Failed to establish database connection.");
        }
    }

    // Close the connection
    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to close database connection.");
        }
    }
}
