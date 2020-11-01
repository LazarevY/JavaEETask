package database.sqlquery;

import core.annotations.InterfaceForType;
import data.query.Delete;
import data.query.Query;

@InterfaceForType(Delete.class)
public interface SQLDeleteQueryMaker extends SQLQueryMaker<Delete<?>> {
}
