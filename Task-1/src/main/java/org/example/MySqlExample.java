package org.example;

import java.sql.*;

public class MySqlExample {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        // Connect to the database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "password");

        // Insert a record
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO employeeDetails (id, name) VALUES (?, ?)");
        insertStatement.setInt(1, 1);
        insertStatement.setString(2, "Ravi");
        int rowsInserted = insertStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Record inserted successfully");
        }

        // Read data
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM employeeDetails");
        while (resultSet.next()) {
            String field1 = resultSet.getString("id");
            String field2 = resultSet.getString("name");
            System.out.println("id: " + field1 + ", name: " + field2);
        }

        // Close the connection
        connection.close();
    }
}