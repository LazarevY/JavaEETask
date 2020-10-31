package database;

import core.annotations.Singleton;

import java.sql.ResultSet;

/**
 * @author Lazarev Yaroslav
 */

@Singleton
public interface DataBase {
    QueryResponse executeQuery(String query);

    QueryResponse executeUpdate(String query);

    void commitTransaction();

    void rollbackTransaction();

    void disconnect();
}
