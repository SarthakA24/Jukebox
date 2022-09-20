package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DatabaseService() {
        this.connection = null;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void printConnectionStatus() {
        if (connection != null) System.out.println("\u001B[32mDatabase is connected.\u001B[0m");
        else System.err.println("!!!Database is NOT connected!!!");
    }
}
