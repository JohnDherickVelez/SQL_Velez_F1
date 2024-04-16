package com.example.csit228_f1_v2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) {
        Connection c = DatabaseConnection.getConnection();

        if (c == null) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        String query = "CREATE TABLE IF NOT EXISTS account (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(25) NOT NULL," +
                "password VARCHAR(100) NOT NULL CHECK(" +
                "    password REGEXP '[A-Z]' AND " + // At least one uppercase letter
                "    password REGEXP '[a-z]' AND " + // At least one lowercase letter
                "    password REGEXP '[0-9]' AND " + // At least one digit
                "    LENGTH(password) >= 8" +        // Minimum length of 8 characters
                "))";

        try {
            Statement statement = c.createStatement();
            statement.execute(query);

            System.out.println("Successfully created the table 'account'");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
