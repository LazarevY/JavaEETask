package logic.expressions.conditions;

import logic.expressions.interfaces.ConditionChecker;
import logic.expressions.interfaces.SpecificComparator;

public class Condition<TargetType, ValueType> {
    private final SpecificComparator<ValueType> comparator;
    private final ConditionChecker<TargetType, ValueType> checker;
    private final ValueType value;

    public Condition(SpecificComparator<ValueType> comparator, ConditionChecker<TargetType, ValueType> checker, ValueType value) {
        this.comparator = comparator;
        this.checker = checker;
        this.value = value;
    }

    public boolean check(TargetType instance){
        return checker.check(instance, value, comparator);
    }
}
