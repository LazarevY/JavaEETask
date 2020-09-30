package data.property;

import logic.events.Birthday;
import logic.events.Event;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class PropertyManagerTest {

    @Test
    public void test000(){

        Event e =
                new Birthday(LocalDate.of(2020, Month.JANUARY, 20),
                        "Desc",
                        "Person",
                        "Gift");
        assertEquals(20, e.getEventDate().getDayOfMonth());
        PropertyManager m =
                new PropertyManager("day");
        m.setValue(e, 12);
        assertEquals(12, e.getEventDate().getDayOfMonth());

    }

    @Test
    public void test001(){

        Birthday e =
                new Birthday(LocalDate.of(2020, Month.JANUARY, 20),
                        "Desc",
                        "Person",
                        "Gift");
        assertEquals("Person", e.getTargetPerson());
        PropertyManager m =
                new PropertyManager("birthdayPerson");
        m.setValue(e, "New Person");
        assertEquals("New Person", e.getTargetPerson());

    }

}