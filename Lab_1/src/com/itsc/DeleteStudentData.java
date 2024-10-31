package com.itsc;

import java.sql.*;

public class DeleteStudentData {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "querywork";
		String databaseName = "StudentsDB";
		
		int studentId = 3;
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			
			String useDatabaseSQL = "USE " + databaseName;
			statement.executeUpdate(useDatabaseSQL);
			
			String deleteSQL = "DELETE FROM students WHERE id = ?";
			PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
			deleteStatement.setInt(1, studentId);
			
			int rowsAffected = deleteStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Student deleted successfully.");
			} else {
				System.out.println("No student found with the specified ID.");
			}
			
			
			statement.close();
			deleteStatement.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
