package database.sql.dialects;

import data.query.Delete;
import data.query.Select;
import data.query.Update;

/**
 * @author Lazarev Yaroslav
 */


public interface SQLDialect {
    String createInsertQuery(Object entity);
    String createDeleteQuery(Delete<?> query);
    String createUpdateQuery(Update<?> query);
    String createSelectQuery(Select<?> query);
}
