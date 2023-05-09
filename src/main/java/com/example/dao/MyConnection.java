package com.example.dao;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class MyConnection {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "password";

    public static Connection createNewConnection() {
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return connection;
        }

        if(connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }

        /*try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("update users set lastname = 'Handson' where id = 1");
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                System.out.println(rs.getInt("id"));;
                System.out.println(rs.getString("lastname"));
            }
        } catch (SQLException e) {
            System.out.println("Statement failed");
            e.printStackTrace();
            return;
        }*/

        return connection;
    }
}
