package com.example.csit228_f1_v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/dbaccount";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";



    static Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("DB connection Success");


        } catch (SQLException e) {
            e.printStackTrace();


        }
        return c;
    }


    public static void main(String[] args) {
        getConnection();
    }
}

