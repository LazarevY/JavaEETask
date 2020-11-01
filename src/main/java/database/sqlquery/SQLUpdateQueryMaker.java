package database.sqlquery;

import core.annotations.InterfaceForType;
import data.query.Query;
import data.query.Update;

@InterfaceForType(Update.class)
public interface SQLUpdateQueryMaker extends SQLQueryMaker<Update<?>> {
}
