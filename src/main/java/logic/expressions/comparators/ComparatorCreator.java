package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

public class ComparatorCreator {
    public static SpecificComparator<?> create(String operation) {
        switch (operation) {
            case ">":
                return (Integer v1, Integer v2) -> v1 > v2;
            case ">=":
                return (Integer v1, Integer v2) -> v1 >= v2;
            case "<":
                return (Integer v1, Integer v2) -> v1 < v2;
            case "<=":
                return (Integer v1, Integer v2) -> v1 <= v2;
            case "=":
                return Object::equals;
            case "!=":
                return (v1, v2) -> !v1.equals(v2);
            default:
                return null;
        }

    }
}
