package com.itsc;

import java.sql.*;

public class RetrieveStudentData {

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
			
			String selectSQL = "SELECT * FROM students LIMIT 5";
			ResultSet resultSet = statement.executeQuery(selectSQL);
			
			System.out.println("ID | FirstName LastName | Grade");
            System.out.println("-------------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int grade = resultSet.getInt("grade");
                System.out.printf("%d | %s %s | %d%n", id, firstname, lastname, grade);
            }
            
            resultSet.close();
            statement.close();
            connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
