package core.database.sql.query;

import core.inverseofcontrol.annotations.InterfaceForType;
import data.query.Delete;

@InterfaceForType(Delete.class)
public interface SQLDeleteQueryMaker extends SQLQueryMaker<Delete<?>> {
}
