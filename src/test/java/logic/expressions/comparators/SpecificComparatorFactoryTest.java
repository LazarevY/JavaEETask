package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpecificComparatorFactoryTest {

    @Test
    public void test000() {
        SpecificComparator<LocalDate> comparator =
                new DataComparatorsFactory().createForOperator(OperatorType.Less);

        LocalDate d1 = LocalDate.of(2020, Month.APRIL, 11);
        LocalDate d2 = LocalDate.of(2020, Month.APRIL, 12);

        assertTrue(comparator.compare(d1, d2));
    }

    @Test
    public void test001() {
        SpecificComparator<LocalDate> comparator =
                new DataComparatorsFactory().createForOperator(OperatorType.Less);

        LocalDate d1 = LocalDate.of(2020, Month.APRIL, 12);
        LocalDate d2 = LocalDate.of(2020, Month.APRIL, 13);

        assertTrue(comparator.compare(d1, d2));
    }

    @Test
    public void test002() {
        SpecificComparator<LocalDate> comparator =
                new DataComparatorsFactory().createForOperator(OperatorType.More);

        LocalDate d1 = LocalDate.of(2020, Month.APRIL, 12);
        LocalDate d2 = LocalDate.of(2020, Month.APRIL, 13);

        assertFalse(comparator.compare(d1, d2));
    }

    @Test
    public void test003() {
        SpecificComparator<LocalTime> comparator =
                ComparatorCreator.getInstance().createComparator(OperatorType.Less, LocalTime.class);
        LocalTime t1 = LocalTime.of(23, 10);
        LocalTime t2 = LocalTime.of(23, 11);

        assertTrue(comparator.compare(t1, t2));

    }

    @Test
    public void test004() {
        SpecificComparator<LocalTime> comparator =
                ComparatorCreator.getInstance().createComparator(OperatorType.NotEqual, LocalTime.class);
        LocalTime t1 = LocalTime.of(23, 10);
        LocalTime t2 = LocalTime.of(23, 11);

        assertTrue(comparator.compare(t1, t2));

    }

    @Test
    public void test005() {
        SpecificComparator<LocalTime> comparator =
                ComparatorCreator.getInstance().createComparator(OperatorType.Equal, LocalTime.class);
        LocalTime t1 = LocalTime.of(23, 10);
        LocalTime t2 = LocalTime.of(23, 10);

        assertTrue(comparator.compare(t1, t2));

    }
}