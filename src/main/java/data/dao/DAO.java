package data.dao;

import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;

import java.util.List;

public interface DAO {

    void insert(Insert<?> insertQuery);
    void delete(Delete<?> deleteQuery);
    List<?> select(Select<?> selectQuery);
    void update(Update<?> updateQuery);

}
