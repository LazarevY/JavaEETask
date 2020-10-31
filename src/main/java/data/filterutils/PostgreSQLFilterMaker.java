package data.filterutils;

import core.annotations.InjectByType;
import data.Filter;
import data.ObjectToPostgreSQLConverter;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLFilterMaker implements SQLFilterMaker {

    @InjectByType
    ObjectToPostgreSQLConverter converter;

    @Override
    public String makeSQLFilter(Filter<?> filter) {
        return String.format("\"%s\"%s%s", filter.getAttribute().toLowerCase(),
                filter.getOperator().getRepresentation(),
                converter.convert(filter.getValue()));
    }
}
