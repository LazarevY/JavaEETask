package logic.expressions.predicates;

import data.AttributeFilterType;
import logic.expressions.conditions.Condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ConditionsPredicate implements Predicate<Object> {

    private final Class dataTypeClass;
    private final HashMap<AttributeFilterType, List<Condition<Object, ?>>> map;


    public ConditionsPredicate(Class dataTypeClass) {
        this.dataTypeClass = dataTypeClass;
        this.map = new HashMap<AttributeFilterType, List<Condition<Object, ?>>>() {{
            put(AttributeFilterType.And, new ArrayList<>());
            put(AttributeFilterType.Or, new ArrayList<>());
            put(AttributeFilterType.Enough, new ArrayList<>());
        }};
    }

    public void addCondition(Condition<Object, Object> condition, AttributeFilterType filterType) {
        map.get(filterType).add(condition);
    }

    @Override
    public boolean test(Object t) {

        boolean allEmpty = true;

        for (List<Condition<Object, ?>> value : map.values()) {
            if (!value.isEmpty()) {
                allEmpty = false;
                break;
            }
        }

        if (allEmpty)
            return true;

        for (Condition<Object, ?> e : map.get(AttributeFilterType.Enough))
            if (e.check(t))
                return true;

        boolean ok = false;
        boolean isOrMapEmpty = map.get(AttributeFilterType.Or).isEmpty();
        if (!isOrMapEmpty) {
            for (Condition<Object, ?> o : map.get(AttributeFilterType.Or))
                if (o.check(t)) {
                    ok = true;
                    break;
                }
            if (!ok)
                return false;
        }

        if (map.get(AttributeFilterType.And).isEmpty() && isOrMapEmpty)
            return false;

        //check And filters
        {
            for (Condition<Object, ?> a : map.get(AttributeFilterType.And))
                if (!a.check(t))
                    return false;
            return true;
        }
    }
}
