package main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/organizer";
    static final String USER = "lazarev";
    static final String PASS = "12345";



    public static void main(String[] args) {

        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Failed to make connection to database");
        }

    }
}
