package logic.expressions.predicates;

import logic.events.Event;
import logic.expressions.conditions.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ConditionsPredicate<T extends Event> implements Predicate<T> {

    private final List<Condition<T, ?>> conditions;

    public ConditionsPredicate() {
        this.conditions = new ArrayList<>();
    }

    public void addCondition(Condition<T, ?> condition){
        conditions.add(condition);
    }

    @Override
    public boolean test(T t) {
        for (Condition<T, ?> condition : conditions)
            if (!condition.check(t))
                return false;
        return true;
    }
}
