package data.dao;

import data.Filter;
import data.property.PropertyManager;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Event;
import logic.expressions.comparators.ComparatorCreator;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.SpecificComparator;
import logic.expressions.predicates.ConditionsPredicate;
import logic.expressions.utils.ExpressionsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
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
        ConditionsPredicate<DataType> predicate =
                new ConditionsPredicate<>();
        for (Filter d : selectQuery.getFilters()){
            Condition<DataType,?> c =
                    new Condition<>((SpecificComparator<Object>) ComparatorCreator.create(d.getOperator()),
                            new PropertyManager(d.getAttribute()),
                            d.getValue());
            predicate.addCondition(c);
        }
        try {
            return dataMap.values().stream().filter(predicate).collect(Collectors.toList());
        }
        catch (ClassCastException e){
            System.err.println("Wrong condtition data type");
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Update<DataType> updateQuery) {

    }
}
