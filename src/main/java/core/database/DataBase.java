package core.database;

import core.database.sql.query.QueryResponse;
import core.inverseofcontrol.annotations.Singleton;

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
