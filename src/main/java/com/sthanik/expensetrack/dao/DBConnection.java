package com.sthanik.expensetrack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String connectionUrl = "jdbc:mysql://localhost:3306/expense_manager";
    static String user="root";
    static String password="root";

    public static Connection getDBConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionUrl, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
