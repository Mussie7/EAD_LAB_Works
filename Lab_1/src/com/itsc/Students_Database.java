package com.itsc;

import java.sql.*;

public class Students_Database {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "querywork";
		String databaseName = "StudentsDB";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			Statement statement = connection.createStatement();
			
			String createDatabaseSQL = "CREATE DATABASE " + databaseName;
			statement.executeUpdate(createDatabaseSQL);
			System.out.println("Database '" + databaseName + "' created successfully.");
			
			String useDatabaseSQL = "USE " + databaseName;
			statement.executeUpdate(useDatabaseSQL);
			
			String createTableSQL = "CREATE TABLE IF NOT EXISTS students(id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), grade INT)";
			statement.executeUpdate(createTableSQL);
			System.out.println("Table 'students' created successfully.");
			
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
