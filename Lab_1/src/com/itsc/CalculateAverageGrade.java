package com.itsc;

import java.sql.*;

public class CalculateAverageGrade {

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
			
			String avgGradeSQL = "SELECT AVG(grade) AS average_grade FROM students";
			ResultSet resultSet = statement.executeQuery(avgGradeSQL);
			
			while (resultSet.next()) {
				double averageGrade = resultSet.getDouble("average_grade");
				System.out.printf("The average grade of all students is: %.2f%n ", averageGrade);
			}
			
			statement.close();
			resultSet.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
