package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

public class StringComparatorsFactory implements SpecificComparatorFactory<String> {
    @Override
    public SpecificComparator<String> createForOperator(String operator) {
        switch (operator) {
            case "=":
                return String::equals;
            case "!=":
                return (s1, s2) -> !s1.equals(s2);
            default:
                return (s1, s2) -> false;
        }
    }
}
