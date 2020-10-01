package data.dao;

import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;

import java.util.List;

public interface DAO<DataType> {

    void insert(Insert<DataType> insertQuery);
    void delete(Delete<DataType> deleteQuery);
    List<DataType> select(Select<DataType> selectQuery);
    void update(Update<DataType> updateQuery);

}
