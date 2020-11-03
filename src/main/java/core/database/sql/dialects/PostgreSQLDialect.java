package core.database.sql.dialects;

import core.database.sql.query.SQLQueryMaker;
import core.database.sql.query.postgresql.PostgreSQLDeleteQueryMaker;
import core.database.sql.query.postgresql.PostgreSQLInsertQueryMaker;
import core.database.sql.query.postgresql.PostgreSQLSelectQueryMaker;
import core.database.sql.query.postgresql.PostgreSQLUpdateQueryMaker;
import core.inverseofcontrol.annotations.InjectByType;
import data.query.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLDialect implements SQLDialect {

    private Map<Class<? extends Query>, SQLQueryMaker<? extends Query<?>>> sqlQueryMakerMap;

    @InjectByType
    private PostgreSQLInsertQueryMaker insertQueryMaker;
    @InjectByType
    private PostgreSQLSelectQueryMaker selectQueryMaker;
    @InjectByType
    private PostgreSQLDeleteQueryMaker deleteQueryMaker;
    @InjectByType
    private PostgreSQLUpdateQueryMaker updateQueryMaker;


    @PostConstruct
    private void init() {
        sqlQueryMakerMap = new HashMap<>();

        sqlQueryMakerMap.put(Insert.class, insertQueryMaker);
        sqlQueryMakerMap.put(Select.class, selectQueryMaker);
        sqlQueryMakerMap.put(Delete.class, deleteQueryMaker);
        sqlQueryMakerMap.put(Update.class, updateQueryMaker);

    }

    @Override
    public String createQuery(Query<?> query) {
        if (!sqlQueryMakerMap.containsKey(query.getClass())) {
            throw new IllegalArgumentException(query.getClass() + " haven't maker impl!");
        }
        return sqlQueryMakerMap.get(query.getClass()).createQuery(query);
    }
}
