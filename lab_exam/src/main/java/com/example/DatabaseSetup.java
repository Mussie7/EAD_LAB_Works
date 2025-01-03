package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/"; // Base URL
        String user = "root";
        String password = "querywork"; 

        try {
            // Connect to MySQL
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Create Bookstore database
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS BookstoreDB";
            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Database created successfully.");

            // Switch to bookstore database
            String useDatabaseSQL = "USE BookstoreDB";
            statement.executeUpdate(useDatabaseSQL);

            // Create books table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Books ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "title VARCHAR(255), "
                    + "author VARCHAR(50), "
                    + "price DOUBLE)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully.");

            // Close the connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
