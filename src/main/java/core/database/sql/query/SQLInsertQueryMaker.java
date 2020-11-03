package core.database.sql.query;

import core.inverseofcontrol.annotations.InterfaceForType;
import data.query.Insert;

@InterfaceForType(Insert.class)
public interface SQLInsertQueryMaker extends SQLQueryMaker<Insert<?>> {
}
