package core.database.sql.dialects;

import core.database.sql.query.SQLQueryMaker;
import core.inverseofcontrol.annotations.MapTypeIfcForType;
import data.query.Query;

import java.util.Map;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLDialect implements SQLDialect {

    @MapTypeIfcForType(SQLQueryMaker.class)
    private Map<Class<? extends Query>, SQLQueryMaker<? extends Query<?>>> sqlQueryMakerMap;


    @Override
    public String createQuery(Query<?> query) {
        if (!sqlQueryMakerMap.containsKey(query.getClass())) {
            throw new IllegalArgumentException(query.getClass() + " haven't maker impl!");
        }
        return sqlQueryMakerMap.get(query.getClass()).createQuery(query);
    }
}
