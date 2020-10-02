package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

import java.time.LocalDate;
import java.util.HashMap;

public class DataComparatorsFactory implements SpecificComparatorFactory<LocalDate> {

    HashMap<OperatorType, SpecificComparator<LocalDate>> map;

    public DataComparatorsFactory() {
        map = new HashMap<OperatorType, SpecificComparator<LocalDate>>() {{
            put(OperatorType.Less, (d1, d2) -> d1.compareTo(d2) < 0);
            put(OperatorType.LessEqual, (d1, d2) -> d1.compareTo(d2) <= 0);
            put(OperatorType.More, (d1, d2) -> d1.compareTo(d2) > 0);
            put(OperatorType.MoreEqual, (d1, d2) -> d1.compareTo(d2) >= 0);
            put(OperatorType.Equal, (d1, d2) -> d1.compareTo(d2) == 0);
            put(OperatorType.NotEqual, (d1, d2) -> d1.compareTo(d2) != 0);
        }};
    }

    @Override
    public SpecificComparator<LocalDate> createForOperator(OperatorType operator) {
        return map.getOrDefault(operator, (d1, d2) -> false);
    }
}
