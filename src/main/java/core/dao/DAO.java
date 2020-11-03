package core.dao;

import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;

import java.util.List;

public interface DAO {

    <T> void insert(Insert<T> insertQuery);

    <T> void delete(Delete<T> deleteQuery);

    <T> List<T> select(Select<T> selectQuery);

    <T> void update(Update<T> updateQuery);

}
