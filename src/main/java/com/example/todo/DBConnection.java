package com.example.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "PostgreSQL JDBCドライバーが見つかりません", e);
        }

        String url = "jdbc:postgresql://localhost:7540/todo-db";
        String user = "postgres";
        String password = "G1o3o5d7tennis";

        return DriverManager.getConnection(url, user, password);
    }
}