package core.database.sql.filter;

import core.database.maping.PostgreObjectMapper;
import core.inverseofcontrol.annotations.InjectByType;
import data.Filter;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLFilterMaker implements SQLFilterMaker {

    @InjectByType
    private PostgreObjectMapper converter;

    @Override
    public String makeSQLFilter(Filter<?> filter) {
        return String.format("\"%s\"%s%s", filter.getAttribute().toLowerCase(),
                filter.getOperator().getRepresentation(),
                converter.convert(filter.getValue()));
    }
}
