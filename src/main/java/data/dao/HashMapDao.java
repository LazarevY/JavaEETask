package data.dao;

import data.AttributeApplier;
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
import utils.common.Cloner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HashMapDao<DataType extends Event> implements DAO<DataType> {

    private final HashMap<Integer, DataType> dataMap = new HashMap<>();

    private static int idCounter = 0;

    @Override
    public void insert(Insert<DataType> insertQuery) {
        for (DataType e : insertQuery.getBody()) {
            e.setId(idCounter++);
            dataMap.put(e.getId(), e);
        }
    }

    @Override
    public void delete(Delete<DataType> deleteQuery) {
        ConditionsPredicate<DataType> predicate = buildPredicate(deleteQuery.getFilters());
        Set<Integer> keysForDelete =
                dataMap.values()
                        .stream()
                        .filter(predicate)
                        .map(Event::getId)
                        .collect(Collectors.toSet());
        for (Integer key : keysForDelete)
            dataMap.remove(key);
    }

    @Override
    public List<DataType> select(Select<DataType> selectQuery) {
        ConditionsPredicate<DataType> predicate = buildPredicate(selectQuery.getFilters());
        try {
            return dataMap.values().stream().filter(predicate).map(Cloner::clone).collect(Collectors.toList());
        } catch (ClassCastException e) {
            System.err.println("Wrong condition data type");
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Update<DataType> updateQuery) {

        ConditionsPredicate<DataType> predicate =
                buildPredicate(updateQuery.getFilters());
        AttributeApplier applier =
                new AttributeApplier(updateQuery.getAttributes());

        dataMap.values()
                .stream()
                .filter(predicate)
                .forEach(applier::applyFor);

    }

    private ConditionsPredicate<DataType> buildPredicate(List<Filter<?>> filters) {
        ConditionsPredicate<DataType> predicate =
                new ConditionsPredicate<>();
        for (Filter<?> d : filters) {
            Condition<DataType, ?> c =
                    new Condition<>(
                            (SpecificComparator<Object>) ComparatorCreator
                                    .getInstance()
                                    .createComparator(d.getOperator(), d.getAttributeClass()),
                            new PropertyManager(d.getAttribute()),
                            d.getValue());
            predicate.addCondition(c);
        }
        return predicate;
    }
}
