package database.sqlquery.postgresql;

import core.annotations.InjectByType;
import data.query.Select;
import database.annotations.Entity;
import database.sqlquery.SQLSelectQueryMaker;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

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
                getAllFields(entityClass).stream().map(Field::getName).map(String::toLowerCase).collect(Collectors.toSet());

        utils.addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }
}
