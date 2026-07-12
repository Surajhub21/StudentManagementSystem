package com.developersuraj.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/Test";

    private static final String USER = "postgres";

    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    private DBConnection() {
    }

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Database Connection Failed. " + e.getMessage(), e);
        }

    }

}