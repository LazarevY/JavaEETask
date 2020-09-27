package data.dao;

import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Event;

import java.util.List;
import java.util.function.Predicate;

public interface DAO<DataType> {

    void insert(Insert<DataType> insertQuery);
    void delete(Delete<DataType> deleteQuery);
    List<DataType> select(Select<DataType> selectQuery);
    void update(Update<DataType> updateQuery);

}
