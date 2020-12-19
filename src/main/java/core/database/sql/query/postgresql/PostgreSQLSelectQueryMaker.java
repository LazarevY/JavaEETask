package core.database.sql.query.postgresql;

import core.database.annotations.Column;
import core.database.annotations.Entity;
import core.database.sql.query.SQLSelectQueryMaker;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.InterfaceForType;
import data.query.Select;

import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

@InterfaceForType(Select.class)
public class PostgreSQLSelectQueryMaker implements SQLSelectQueryMaker {

    @InjectByType
    private PostgreSQLQueryUtils utils;

    @Override
    public String makeQuery(Select<?> query) {
        Class entityClass = query.getDataTypeClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        utils.addTableName(entityClass, builder);

        Set<String> fieldsName =
                getAllFields(entityClass).stream().filter(f->f.isAnnotationPresent(Column.class))
                        .map(field -> {
                            Column annotation = field.getAnnotation(Column.class);
                            return annotation.value().equals("")? field.getName(): annotation.value();
                        }).map(String::toLowerCase).collect(Collectors.toSet());

        utils.addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }
}
