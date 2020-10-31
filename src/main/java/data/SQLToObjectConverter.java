package data;

import java.sql.ResultSet;

public interface SQLToObjectConverter {
    <T> T convert(ResultSet set, String  colName, Class<T> type);
}
