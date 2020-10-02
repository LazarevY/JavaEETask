package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

import java.time.LocalTime;
import java.util.HashMap;

public class TimeComparatorsFactory implements SpecificComparatorFactory<LocalTime> {

    private HashMap<OperatorType, SpecificComparator<LocalTime>> map;

    public TimeComparatorsFactory() {
        map = new HashMap<OperatorType, SpecificComparator<LocalTime>>() {{
            put(OperatorType.Less, (t1, t2) -> t1.compareTo(t2) < 0);
            put(OperatorType.LessEqual, (t1, t2) -> t1.compareTo(t2) <= 0);
            put(OperatorType.More, (t1, t2) -> t1.compareTo(t2) > 0);
            put(OperatorType.MoreEqual, (t1, t2) -> t1.compareTo(t2) >= 0);
            put(OperatorType.Equal, (t1, t2) -> t1.compareTo(t2) == 0);
            put(OperatorType.NotEqual, (t1, t2) -> t1.compareTo(t2) != 0);
        }};
    }

    @Override
    public SpecificComparator<LocalTime> createForOperator(OperatorType operator) {
        return map.getOrDefault(operator, (t1, t2) -> false);
    }
}
