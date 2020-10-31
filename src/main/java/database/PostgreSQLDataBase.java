package database;

import core.annotations.InjectProperty;
import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;

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
            connection.setAutoCommit(false);
        }


    }

    @Override
    @SneakyThrows
    public QueryResponse executeQuery(String query) {
        ResultSet resultSet;
        QueryResponse response = new QueryResponse(null, 0, "");
        try(PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
        }
        catch (SQLException e){
            response.setCode(e.getErrorCode());
            response.setMsg(e.toString());
            if (e.getErrorCode() != 0) {
                e.printStackTrace();
                rollbackTransaction();
                return response;
            }
            return response;
        }

        response.setResultSet(resultSet);
        commitTransaction();
        return response;
    }

    @Override
    @SneakyThrows
    public QueryResponse executeUpdate(String query) {
        QueryResponse response = new QueryResponse(null, 0, "");
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.executeUpdate();
        }
        catch (SQLException e){
            response.setMsg(e.toString());
            response.setCode(e.getErrorCode());
            e.printStackTrace();
            rollbackTransaction();
            return response;
        }
        commitTransaction();
        return response;
    }

    @SneakyThrows
    @Override
    public void disconnect() {
        connection.close();
        System.out.println("Database disconnected successfully");
    }

    @Override
    @SneakyThrows
    public void commitTransaction() {
        connection.commit();
    }

    @Override
    @SneakyThrows
    public void rollbackTransaction() {
        connection.rollback();
    }
}
