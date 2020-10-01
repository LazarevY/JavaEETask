package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class SpecificComparatorFactoryTest {

    @Test
    public void test000() {
        SpecificComparator<LocalDate> comparator =
                new DataComparatorsFactory().createForOperator("<");

        LocalDate d1 = LocalDate.of(2020, Month.APRIL, 12);
        LocalDate d2 = LocalDate.of(2020, Month.APRIL, 11);

        assertTrue(comparator.compare(d1, d2));
    }

    @Test
    public void test001() {
        SpecificComparator<LocalDate> comparator =
                new DataComparatorsFactory().createForOperator("<");

        LocalDate d1 = LocalDate.of(2020, Month.APRIL, 12);
        LocalDate d2 = LocalDate.of(2020, Month.APRIL, 13);

        assertFalse(comparator.compare(d1, d2));
    }

    @Test
    public void test002() {
        SpecificComparator<LocalDate> comparator =
                new DataComparatorsFactory().createForOperator(">");

        LocalDate d1 = LocalDate.of(2020, Month.APRIL, 12);
        LocalDate d2 = LocalDate.of(2020, Month.APRIL, 13);

        assertTrue(comparator.compare(d1, d2));
    }
}