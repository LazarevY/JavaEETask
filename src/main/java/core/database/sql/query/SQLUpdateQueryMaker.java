package core.database.sql.query;

import core.inverseofcontrol.annotations.InterfaceForType;
import data.query.Update;

@InterfaceForType(Update.class)
public interface SQLUpdateQueryMaker extends SQLQueryMaker<Update<?>> {
}
