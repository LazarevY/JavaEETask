package data.dao;

import core.annotations.InjectByType;
import data.ObjectToPostgreSQLConverter;
import data.SQLToObjectConverter;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import database.DataBase;
import database.QueryResponse;
import database.SQLContext;
import database.annotations.Column;
import lombok.SneakyThrows;

import java.awt.desktop.QuitEvent;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllFields;

public class SQLBasedDAO implements DAO {

    @InjectByType
    private SQLContext context;

    @InjectByType
    private SQLToObjectConverter converter;

    @Override
    public <T> void insert(Insert<T> insertQuery) {
        QueryResponse response = context.execQuery(insertQuery);
    }

    @Override
    public <T> void delete(Delete<T> deleteQuery) {
        QueryResponse response = context.execQuery(deleteQuery);
    }

    @Override
    @SneakyThrows
    public <T> List<T> select(Select<T> selectQuery) {
        List<T> entities = new ArrayList<>();

        QueryResponse response = context.execQuery(selectQuery);

        if (response.getCode() != 0 || response.getResultSet() == null)
            return entities;

        ResultSet set = response.getResultSet();

        Set<Field> fields = getAllFields(selectQuery.getDataTypeClass(),
                field -> field.isAnnotationPresent(Column.class));


        while (set.next()) {

            T entity = (T) selectQuery.getDataTypeClass().getDeclaredConstructor().newInstance();

            for (Field field : fields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                String colName = column.value().isEmpty() ?
                        field.getName() : column.value();
                colName = colName.toLowerCase();
                Object val = converter.convert(set, colName, field.getType());

                if (val == null)
                    throw new IllegalArgumentException("No know how conversion for column " + colName);

                field.set(entity, val);
            }

            entities.add(entity);

        }

        return entities;

    }

    @Override
    public <T> void update(Update<T> updateQuery) {
        context.execQuery(updateQuery);
    }
}
