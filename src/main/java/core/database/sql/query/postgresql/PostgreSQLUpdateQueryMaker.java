package core.database.sql.query.postgresql;

import core.database.annotations.Entity;
import core.database.maping.PostgreObjectMapper;
import core.database.sql.query.SQLUpdateQueryMaker;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.InterfaceForType;
import data.Attribute;
import data.query.Update;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

@InterfaceForType(Update.class)
public class PostgreSQLUpdateQueryMaker implements SQLUpdateQueryMaker {

    @InjectByType
    private PostgreSQLQueryUtils utils;

    @InjectByType
    private PostgreObjectMapper converter;

    @Override
    public String makeQuery(Update<?> query) {
        Class entityClass = query.getDataTypeClass();

        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(entityClass + " is not entity");
        }

        List<Attribute> attributes;
        Set<String> fieldsName =
                getAllFields(entityClass)
                        .stream()
                        .map(Field::getName)
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());
        attributes = query.getAttributes()

                .stream()
                .filter(attribute -> fieldsName.contains(attribute.getName().toLowerCase()))
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
            builder.append(String.format("\"%s\" = %s,", attribute.getName().toLowerCase(), converter.convert(attribute.getValue())));
        }
        builder.deleteCharAt(builder.length() - 1);

        utils.addWherePredicateIfNeeded(builder, fieldsName, query.getFilters());
        builder.append(";");
        return builder.toString();
    }
}
