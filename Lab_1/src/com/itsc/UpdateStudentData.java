package com.itsc;

import java.sql.*;

public class UpdateStudentData {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "querywork";
		String databaseName = "StudentsDB";
		
		int studentId = 2;
		String newFristName = "UpdatedFirstName";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			
			String useDatabaseSQL = "USE " + databaseName;
			statement.executeUpdate(useDatabaseSQL);
			
			String updateSQL = "UPDATE students SET firstname = ? WHERE id = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
			updateStatement.setString(1, newFristName);
			updateStatement.setInt(2, studentId);
			
			int rowsAffected = updateStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Student's firsname updated successfully.");
			} else {
				System.out.println("No student found with the specified ID.");
			}
			
			statement.close();
			updateStatement.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
