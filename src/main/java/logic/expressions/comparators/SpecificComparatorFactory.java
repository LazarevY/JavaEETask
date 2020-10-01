package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

public interface SpecificComparatorFactory<T> {
    SpecificComparator<T> createForOperator(String operator);
}
