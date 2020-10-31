package database.sql.dialects;

import core.annotations.InjectByType;
import data.Attribute;
import data.Filter;
import data.filterutils.SQLFilterMaker;
import data.query.*;
import database.annotations.AutoGen;
import database.annotations.Column;
import database.annotations.Entity;
import database.annotations.Id;
import database.sqlquery.SQLQueryMaker;
import database.sqlquery.postgresql.PostgreSQLDeleteQueryMaker;
import database.sqlquery.postgresql.PostgreSQLInsertQueryMaker;
import database.sqlquery.postgresql.PostgreSQLSelectQueryMaker;
import database.sqlquery.postgresql.PostgreSQLUpdateQueryMaker;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

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
