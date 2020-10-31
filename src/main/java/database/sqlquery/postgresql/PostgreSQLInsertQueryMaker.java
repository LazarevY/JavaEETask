package database.sqlquery.postgresql;

import core.annotations.InjectByType;
import data.ObjectToPostgreSQLConverter;
import data.query.Insert;
import database.annotations.AutoGen;
import database.annotations.Column;
import database.annotations.Entity;
import database.annotations.Id;
import database.sqlquery.SQLInsertQueryMaker;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

public class PostgreSQLInsertQueryMaker implements SQLInsertQueryMaker {

    @InjectByType
    private PostgreSQLQueryUtils utils;

    @InjectByType
    private ObjectToPostgreSQLConverter converter;

    @Override
    public String makeQuery(Insert<?> query) {
        String q;
        try {
            q = query.getBody()
                    .stream()
                    .map(this::makeQueryForEntity)
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return q;
    }

    @SneakyThrows
    private String makeQueryForEntity(Object entityObject) {
        Class entityClass = entityObject.getClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO");
        builder.append(" ");

        utils.addTableName(entityClass, builder);
        builder.append(" (");

        List<Map.Entry<String, Object>> columns = new ArrayList<>();

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
                Object colValue = field.get(entityObject);
                columns.add(new AbstractMap.SimpleEntry<>(colName, colValue));
            }
        }

        for (Map.Entry<String, Object> column : columns) {
            builder.append(String.format("`%s`,", column.getKey()));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(") VALUES (");

        for (Map.Entry<String, Object> column : columns) {
            builder.append(String.format("%s,", converter.convert(column.getValue())));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(");");

        return builder.toString();
    }
}
