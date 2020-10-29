package database.sql.dialects;

import core.annotations.InjectByType;
import data.Attribute;
import data.Filter;
import data.filterutils.SQLFilterMaker;
import data.query.Delete;
import data.query.Select;
import data.query.Update;
import database.annotations.AutoGen;
import database.annotations.Column;
import database.annotations.Entity;
import database.annotations.Id;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

/**
 * @author Lazarev Yaroslav
 */


public class PostgreSQLDialect implements SQLDialect {

    @InjectByType
    private SQLFilterMaker filterMaker;

    @Override
    @SneakyThrows
    public String createInsertQuery(Object entityObject) {
        Class entityClass = entityObject.getClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO");
        builder.append(" ");

        addTableName(entityClass, builder);
        builder.append(" (");

        List<Map.Entry<String, String>> columns = new ArrayList<>();

        for (Field field : getAllFields(entityClass)) {
            if (field.isAnnotationPresent(Id.class)) {
                Id id = field.getAnnotation(Id.class);
                if (id.autoGen() == AutoGen.TRUE)
                    continue;
            }
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                Column c = field.getAnnotation(Column.class);
                String colName = c.value().isEmpty() ? field.getName() : c.value();
                String colValue = field.get(entityObject).toString();
                columns.add(new AbstractMap.SimpleEntry<>(colName, colValue));
            }
        }

        for (Map.Entry<String, String> column : columns) {
            builder.append(String.format("`%s`,", column.getKey()));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(") VALUES (");

        for (Map.Entry<String, String> column : columns) {
            builder.append(String.format("`%s`,", column.getValue()));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(");");

        return builder.toString();

    }

    @Override
    public String createDeleteQuery(Delete<?> query) {
        Class entityClass = query.getDataTypeClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM");
        builder.append(" ");

        addTableName(entityClass, builder);

        Set<String> fieldsName =
                getAllFields(entityClass).stream().map(Field::getName).collect(Collectors.toSet());

        addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }

    @Override
    public String createUpdateQuery(Update<?> query) {
        Class entityClass = query.getDataTypeClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        List<Attribute> attributes;
        Set<String> fieldsName =
                getAllFields(entityClass).stream().map(Field::getName).collect(Collectors.toSet());
        attributes = query.getAttributes()

                .stream().filter(attribute -> fieldsName.contains(attribute.getName()))
                .collect(Collectors.toList());

        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("No have valid attributes to set");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE");
        builder.append(" ");

        addTableName(entityClass, builder);

        builder.append(" SET");

        for (Attribute attribute : attributes) {
            builder.append(" ");
            builder.append(String.format("`%s` = `%s`,", attribute.getName(), attribute.getValue().toString()));
        }
        builder.deleteCharAt(builder.length() - 1);

        addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }

    @Override
    public String createSelectQuery(Select<?> query) {
        Class entityClass = query.getDataTypeClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        addTableName(entityClass, builder);

        Set<String> fieldsName =
                getAllFields(entityClass).stream().map(Field::getName).collect(Collectors.toSet());

        addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }

    private void addWherePredicateIfNeeded(StringBuilder builder, Set<String> fieldsName, List<Filter<?>> filters2) {
        List<Filter<?>> filters = filterFilters(filters2, fieldsName);

        if (!filters.isEmpty()) {
            builder.append(" WHERE");

            for (Filter<?> filter : filters) {
                builder.append(" ");
                builder.append(filterMaker.makeSQLFilter(filter));
                builder.append(" AND");
            }

            builder.deleteCharAt(builder.length() - 1);
            builder.deleteCharAt(builder.length() - 1);
            builder.deleteCharAt(builder.length() - 1);
            builder.deleteCharAt(builder.length() - 1);
        }

    }

    private void addTableName(Class entityClass, StringBuilder builder) {
        String table;
        Entity entity = (Entity) entityClass.getDeclaredAnnotation(Entity.class);
        table = entity.value().isEmpty() ? entityClass.getSimpleName().toLowerCase() : entity.value();
        builder.append(table);
    }

    private List<Filter<?>> filterFilters(List<Filter<?>> filters, Set<String> fieldsName) {
        return filters
                .stream()
                .filter(filter -> fieldsName.contains(filter.getAttribute()))
                .collect(Collectors.toList());
    }
}
