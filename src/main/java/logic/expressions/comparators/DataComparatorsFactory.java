package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

import java.time.LocalDate;
import java.util.HashMap;

public class DataComparatorsFactory implements SpecificComparatorFactory<LocalDate> {

    HashMap<String, SpecificComparator<LocalDate>> map;

    public DataComparatorsFactory() {
        map = new HashMap<String, SpecificComparator<LocalDate>>() {{
            put("<", (d1, d2) -> d1.compareTo(d2) > 0);
            put("<=", (d1, d2) -> d1.compareTo(d2) >= 0);
            put(">", (d1, d2) -> d1.compareTo(d2) < 0);
            put(">=", (d1, d2) -> d1.compareTo(d2) <= 0);
            put("=", (d1, d2) -> d1.compareTo(d2) == 0);
            put("!=", (d1, d2) -> d1.compareTo(d2) != 0);
        }};
    }

    @Override
    public SpecificComparator<LocalDate> createForOperator(String operator) {
        return map.getOrDefault(operator, (d1, d2) -> false);
    }
}
