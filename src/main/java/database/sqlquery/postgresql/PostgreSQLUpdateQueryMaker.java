package database.sqlquery.postgresql;

import core.annotations.InjectByType;
import data.Attribute;
import data.ObjectToPostgreSQLConverter;
import data.query.Update;
import database.annotations.Entity;
import database.sqlquery.SQLUpdateQueryMaker;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

public class PostgreSQLUpdateQueryMaker implements SQLUpdateQueryMaker {

    @InjectByType
    private PostgreSQLQueryUtils utils;

    @InjectByType
    ObjectToPostgreSQLConverter converter;

    @Override
    public String makeQuery(Update<?> query) {
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

        utils.addTableName(entityClass, builder);

        builder.append(" SET");

        for (Attribute attribute : attributes) {
            builder.append(" ");
            builder.append(String.format("`%s` = %s,", attribute.getName(), converter.convert(attribute.getValue())));
        }
        builder.deleteCharAt(builder.length() - 1);

        utils.addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }
}
