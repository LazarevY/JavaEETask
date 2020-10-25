package database;

import core.annotations.InjectProperty;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.sql.*;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLDataBase implements DataBase {

    @InjectProperty()
    private String dataBaseUrl;

    @InjectProperty
    private String dataBaseUser;

    @InjectProperty
    private String databasePass;

    private Connection connection;

    @SneakyThrows
    @PostConstruct
    void execConnect(){
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager
                    .getConnection(dataBaseUrl, dataBaseUser, databasePass);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            connection.setAutoCommit(true);
        }
    }

    @Override
    @SneakyThrows
    public ResultSet executeQuery(String query) {
        ResultSet resultSet;
        try(PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
        }

        return resultSet;
    }

    @Override
    @SneakyThrows
    public void executeUpdate(String query) {
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void disconnect() {
        connection.close();
        System.out.println("Database disconnected successfully");
    }
}
