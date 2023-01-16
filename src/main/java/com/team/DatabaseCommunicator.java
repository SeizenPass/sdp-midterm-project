package com.team;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database connection with singleton pattern
 */
@Data
public class DatabaseCommunicator {

    private static DatabaseCommunicator instance;

    private DatabaseCommunicator(){}

    private Connection conn = null;
    private Statement stmt = null;

    public static DatabaseCommunicator getInstance() {
        if (instance == null) {
            instance = new DatabaseCommunicator();
        }
        return instance;
    }

    public void connect() {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/flower_store",
                            "postgres", "123");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}