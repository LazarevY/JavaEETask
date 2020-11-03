package core.database.sql.query.postgresql;

import core.database.annotations.Entity;
import core.database.sql.query.SQLDeleteQueryMaker;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.InterfaceForType;
import data.query.Delete;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

@InterfaceForType(Delete.class)
public class PostgreSQLDeleteQueryMaker implements SQLDeleteQueryMaker {

    @InjectByType
    private PostgreSQLQueryUtils utils;

    @Override
    public String makeQuery(Delete<?> query) {
        Class entityClass = query.getDataTypeClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM");
        builder.append(" ");

        utils.addTableName(entityClass, builder);

        Set<String> fieldsName =
                getAllFields(entityClass).stream().map(Field::getName).map(String::toLowerCase).collect(Collectors.toSet());

        utils.addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }


}
