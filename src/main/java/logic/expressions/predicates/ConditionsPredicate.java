package logic.expressions.predicates;

import data.AttributeFilterType;
import logic.events.Event;
import logic.expressions.conditions.Condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ConditionsPredicate<T extends Event> implements Predicate<T> {

    private HashMap<AttributeFilterType, List<Condition<T, ?>>> map;


    public ConditionsPredicate() {
        this.map = new HashMap<AttributeFilterType, List<Condition<T, ?>>>(){{
            put(AttributeFilterType.And, new ArrayList<>());
            put(AttributeFilterType.Or, new ArrayList<>());
            put(AttributeFilterType.Enough, new ArrayList<>());
        }};
    }

    public void addCondition(Condition<T, ?> condition, AttributeFilterType filterType){
        map.get(filterType).add(condition);
    }

    @Override
    public boolean test(T t) {

        for (Condition<T, ?> e: map.get(AttributeFilterType.Enough))
            if (e.check(t))
                return true;

        boolean ok = false;
        boolean isOrMapEmpty = map.get(AttributeFilterType.Or).isEmpty();
        if (!isOrMapEmpty) {
            for (Condition<T, ?> o : map.get(AttributeFilterType.Or))
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
            for (Condition<T, ?> a : map.get(AttributeFilterType.And))
                if (!a.check(t))
                    return false;
            return true;
        }
    }
}
