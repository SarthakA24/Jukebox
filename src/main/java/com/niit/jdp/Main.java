package com.niit.jdp;

import com.niit.jdp.service.DatabaseService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an object for the DatabaseService class
            DatabaseService databaseService = new DatabaseService();
            // Connect to the database
            databaseService.connect();
            // Print the connection status
            databaseService.printConnectionStatus();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}