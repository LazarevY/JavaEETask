package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

import java.util.HashMap;

public class IntegerComparatorsFactory implements SpecificComparatorFactory<Integer> {

    HashMap<OperatorType, SpecificComparator<Integer>> comparatorHashSet;

    public IntegerComparatorsFactory() {
        this.comparatorHashSet = new HashMap<OperatorType, SpecificComparator<Integer>>() {{
            put(OperatorType.Less, (i1, i2) -> i1 < i2);
            put(OperatorType.LessEqual, (i1, i2) -> i1 <= i2);
            put(OperatorType.More, (i1, i2) -> i1 > i2);
            put(OperatorType.MoreEqual, (i1, i2) -> i1 >= i2);
            put(OperatorType.Equal, Integer::equals);
            put(OperatorType.NotEqual, (i1, i2) -> !i1.equals(i2));
        }};
    }

    @Override
    public SpecificComparator<Integer> createForOperator(OperatorType operator) {
        return comparatorHashSet.getOrDefault(operator, (v1, v2) -> false);
    }
}
