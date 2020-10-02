package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

public class StringComparatorsFactory implements SpecificComparatorFactory<String> {
    @Override
    public SpecificComparator<String> createForOperator(OperatorType operator) {
        switch (operator) {
            case Equal:
                return String::equals;
            case NotEqual:
                return (s1, s2) -> !s1.equals(s2);
            default:
                return (s1, s2) -> false;
        }
    }
}
