package core.database.maping;

import java.sql.ResultSet;

public interface SQLMapper {
    <T> T convert(ResultSet set, String colName, Class<T> type);
}
