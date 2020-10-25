package database;

import core.annotations.Singleton;

import java.sql.ResultSet;

/**
 * @author Lazarev Yaroslav
 */

@Singleton
public interface DataBase {
    ResultSet executeQuery(String query);

    void executeUpdate(String query);

    void disconnect();
}
