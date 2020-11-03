package core.dao;


import data.AttributeApplier;
import data.Filter;
import data.property.PropertyManager;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.ComparatorCreator;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.SpecificComparator;
import logic.expressions.predicates.ConditionsPredicate;
import utils.common.Cloner;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HashMapDao implements DAO {

    private static int idCounter = 0;
    private final Map<Class<? extends Event>, Map<Integer, Event>> dataMap = new HashMap<>();

    @PostConstruct
    private void init() {
        dataMap.put(Appointment.class, new HashMap<>());
        dataMap.put(Birthday.class, new HashMap<>());
    }

    @Override
    public <T> void insert(Insert<T> insertQuery) {
        for (Object e : insertQuery.getBody()) {
            if (!(e instanceof Event))
                throw new IllegalArgumentException(e.getClass() + " not supported");
            Event event = (Event) e;
            event.setId(idCounter++);
            dataMap.get(e.getClass()).put(event.getId(), event);
        }
    }

    @Override
    public <T> void delete(Delete<T> deleteQuery) {
        Predicate<Object> predicate = buildPredicate(deleteQuery.getFilters(), deleteQuery.getDataTypeClass());
        List<Map<Integer, Event>> maps = getMapListForType(deleteQuery.getDataTypeClass());
        for (Map<Integer, Event> map : maps) {
            Set<Integer> keysForDelete =
                    map.values()
                            .stream()
                            .filter(predicate)
                            .map(Event::getId)
                            .collect(Collectors.toSet());

            for (Integer key : keysForDelete)
                map.remove(key);
        }
    }

    private List<Map<Integer, Event>> getMapListForType(Class<?> dataTypeClass) {
        if (dataTypeClass == Event.class)
            return new ArrayList<>(dataMap.values());
        else return Collections.singletonList(dataMap.getOrDefault(dataTypeClass, new HashMap<>()));
    }

    @Override
    public <T> List<T> select(Select<T> selectQuery) {
        Predicate<Object> predicate = buildPredicate(selectQuery.getFilters(), selectQuery.getDataTypeClass());
        List<Event> events = new ArrayList<>();

        List<Map<Integer, Event>> mapListForType = getMapListForType(selectQuery.getDataTypeClass());

        for (Map<Integer, Event> map : mapListForType) {
            events.addAll(
                    map.values().stream().filter(predicate).map(Cloner::clone).collect(Collectors.toList())
            );
        }

        return new ArrayList<T>((Collection<? extends T>) events);

    }

    @Override
    public <T> void update(Update<T> updateQuery) {

        Predicate predicate =
                buildPredicate(updateQuery.getFilters(), updateQuery.getDataTypeClass());
        AttributeApplier applier =
                new AttributeApplier(updateQuery.getAttributes());

        for (Map<Integer, Event> map : getMapListForType(updateQuery.getDataTypeClass())) {
            map.values().stream().filter(predicate).forEach(applier::applyFor);
        }

    }

    private Predicate<Object> buildPredicate(List<Filter<?>> filters, Class dataType) {
        ConditionsPredicate predicate =
                new ConditionsPredicate(dataType);
        for (Filter<?> d : filters) {
            Condition<Object, Object> c =
                    new Condition<>(
                            (SpecificComparator<Object>) ComparatorCreator
                                    .getInstance()
                                    .createComparator(d.getOperator(), d.getAttributeClass()),
                            new PropertyManager(d.getAttribute()),
                            d.getValue());
            predicate.addCondition(c, d.getType());
        }
        return predicate;
    }
}
