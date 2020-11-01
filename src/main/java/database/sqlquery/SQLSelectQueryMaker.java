package database.sqlquery;

import core.annotations.InterfaceForType;
import data.query.Query;
import data.query.Select;

@InterfaceForType(Select.class)
public interface SQLSelectQueryMaker extends SQLQueryMaker<Select<?>> {
}
