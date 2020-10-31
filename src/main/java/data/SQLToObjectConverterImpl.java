package data;

import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class SQLToObjectConverterImpl implements SQLToObjectConverter {
    @Override
    @SneakyThrows
    public <T> T convert(ResultSet set, String colName, Class<T> type) {
        if (set.findColumn(colName) < 1)
            return null;

        if (type == String.class)
            return (T) set.getString(colName);
        else if (type == Integer.class || type == int.class)
            return (T) (Integer) set.getInt(colName);
        else if (type == Boolean.class)
            return (T) (Boolean) set.getBoolean(colName);
        else if (type == LocalDate.class)
            return (T)set.getDate(colName).toLocalDate();
        else if(type == LocalTime.class)
            return (T)set.getTime(colName).toLocalTime();

        return null;

    }
}
