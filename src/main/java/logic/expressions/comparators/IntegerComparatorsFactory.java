package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

import java.util.HashMap;

public class IntegerComparatorsFactory implements SpecificComparatorFactory<Integer> {

    HashMap<String, SpecificComparator<Integer>> comparatorHashSet;

    public IntegerComparatorsFactory() {
        this.comparatorHashSet = new HashMap<String, SpecificComparator<Integer>>() {{
            put("<", (i1, i2) -> i1 < i2);
            put("<=", (i1, i2) -> i1 <= i2);
            put(">", (i1, i2) -> i1 > i2);
            put(">=", (i1, i2) -> i1 >= i2);
            put("=", Integer::equals);
            put("!=", (i1, i2) -> !i1.equals(i2));
        }};
    }

    @Override
    public SpecificComparator<Integer> createForOperator(String operator) {
        return comparatorHashSet.getOrDefault(operator, (v1, v2) -> false);
    }
}
