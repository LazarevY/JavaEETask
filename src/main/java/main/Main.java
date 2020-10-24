package main;

import java.sql.*;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/lazarev";
    static final String USER = "lazarev";
    static final String PASS = "12345";



    public static void main(String[] args) throws SQLException{

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

            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM car_sharing_schema.autos")){

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    System.out.println(resultSet.getString(4));
                }

            } finally {
                connection.close();
            }

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
