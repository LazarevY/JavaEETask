package core.database.sql.query;

import core.inverseofcontrol.annotations.InterfaceForType;
import data.query.Select;

@InterfaceForType(Select.class)
public interface SQLSelectQueryMaker extends SQLQueryMaker<Select<?>> {
}
