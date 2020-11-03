package logic.expressions.interfaces;

@FunctionalInterface
public interface ConditionChecker<TargetType, ValueType> {
    boolean check(TargetType instance, ValueType value, SpecificComparator<ValueType> comparator);
}
