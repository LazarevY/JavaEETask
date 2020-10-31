package database.sql.dialects;

import data.query.Delete;
import data.query.Query;
import data.query.Select;
import data.query.Update;

/**
 * @author Lazarev Yaroslav
 */


public interface SQLDialect {
    String createQuery(Query<?> query);
}
