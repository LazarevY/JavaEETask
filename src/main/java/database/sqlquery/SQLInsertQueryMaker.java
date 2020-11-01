package database.sqlquery;

import core.annotations.InterfaceForType;
import data.query.Insert;
import data.query.Query;

@InterfaceForType(Insert.class)
public interface SQLInsertQueryMaker extends SQLQueryMaker<Insert<?>> {
}
