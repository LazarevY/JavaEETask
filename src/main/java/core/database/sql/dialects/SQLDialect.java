package core.database.sql.dialects;

import data.query.Query;

/**
 * @author Lazarev Yaroslav
 */


public interface SQLDialect {
    String createQuery(Query<?> query);
}
