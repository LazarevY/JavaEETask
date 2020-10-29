package data.filterutils;

import data.Filter;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLFilterMaker implements SQLFilterMaker {
    @Override
    public String makeSQLFilter(Filter<?> filter) {
        return String.format("`%s`%s`%s`", filter.getAttribute(), filter.getOperator().getRepresentation(), filter.getValue().toString());
    }
}
