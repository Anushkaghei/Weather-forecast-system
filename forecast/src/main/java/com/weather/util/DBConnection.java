package com.weather.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/weather_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Gobbles#77"; // Replace with your MySQL password

    // Static method to obtain a connection to the database
    public static Connection getConnection() {
        try {
            // Ensure the JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create and return a connection to the database
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // Print any exceptions to standard error
            e.printStackTrace();
            // Return null if the connection could not be established
            return null;
        }
    }

    // Method to test the database connection
    public static boolean testConnection() {
        Connection connection = null;
        try {
            // Attempt to establish a connection
            connection = getConnection();
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close the connection if it was opened
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
