package com.itsc;

import java.sql.*;

public class InsertStudentData {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "querywork";
		String databaseName = "StudentsDB";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			
			String useDatabaseSQL = "USE " + databaseName;
			statement.executeUpdate(useDatabaseSQL);
			
			String insertSQL = "INSERT INTO students(id, firstname, lastname, grade) VALUE (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "John");
			preparedStatement.setString(3, "Doe");
			preparedStatement.setInt(4, 90);
			preparedStatement.executeUpdate();
			
			String[][] studentData = {
					{"2", "Alice", "Smith", "85"},
	                {"3", "Bob", "Johnson", "78"},
	                {"4", "Cathy", "Brown", "92"},
	                {"5", "David", "Jones", "88"},
	                {"6", "Eve", "Davis", "76"},
	                {"7", "Frank", "Wilson", "84"},
	                {"8", "Grace", "Taylor", "91"},
	                {"9", "Henry", "Moore", "75"},
	                {"10", "Ivy", "Anderson", "89"},
	                {"11", "Jack", "Thomas", "83"}
			};
			
			for (String[] student : studentData) {
				preparedStatement.setInt(1, Integer.parseInt(student[0]));
				preparedStatement.setString(2, student[1]);
				preparedStatement.setString(3, student[2]);
				preparedStatement.setInt(4, Integer.parseInt(student[3]));
				preparedStatement.executeUpdate();
			}
			
			System.out.println("Student data inserted successfully.");
			
			statement.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
