package core.database.sql.query.postgresql;

import core.database.annotations.Entity;
import core.database.sql.filter.PostgreSQLFilterMaker;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.Singleton;
import data.Filter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class PostgreSQLQueryUtils {

    @InjectByType
    private PostgreSQLFilterMaker filterMaker;

    public void addWherePredicateIfNeeded(StringBuilder builder, Set<String> fieldsName, List<Filter<?>> filters2) {
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

    public void addTableName(Class entityClass, StringBuilder builder) {
        String table;
        Entity entity = (Entity) entityClass.getDeclaredAnnotation(Entity.class);
        table = entity.value().isEmpty() ? entityClass.getSimpleName().toLowerCase() : entity.value();
        builder.append(table);
    }

    public List<Filter<?>> filterFilters(List<Filter<?>> filters, Set<String> fieldsName) {
        return filters
                .stream()
                .filter(filter -> fieldsName.contains(filter.getAttribute()))
                .collect(Collectors.toList());
    }
}
