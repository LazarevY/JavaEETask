package data.dao;

import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapDao<DataType extends Event> implements DAO<DataType> {

    private HashMap<Integer, DataType> dataMap = new HashMap<>();

    private static int idCounter = 0;

    @Override
    public void insert(Insert<DataType> insertQuery) {
        for (DataType e : insertQuery.getBody()){
            e.setId(idCounter++);
            dataMap.put(e.getId(), e);
        }
    }

    @Override
    public void delete(Delete<DataType> deleteQuery) {

    }

    @Override
    public List<DataType> select(Select<DataType> selectQuery) {
        return new ArrayList<>(dataMap.values());
    }

    @Override
    public void update(Update<DataType> updateQuery) {

    }
}
