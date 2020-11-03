package core.database.sql.filter;

import data.Filter;

/**
 * @author Lazarev Yaroslav
 */


public interface SQLFilterMaker {
    String makeSQLFilter(Filter<?> filter);
}
