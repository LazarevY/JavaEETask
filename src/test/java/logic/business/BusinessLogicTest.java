package logic.business;

import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.dao.HashMapDao;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BusinessLogicTest {

    @Test
    public void test000(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday birthday =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Collections.singletonList(birthday));

        List<Event> birthdays = logic.getAllEvents(Collections.emptyList());

        assertEquals(1, birthdays.size());

    }

    @Test
    public void test001(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday birthday =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Collections.singletonList(birthday));

        List<Appointment> appointments = logic.listOf(Collections.emptyList(), Appointment.class);
        List<Birthday> birthdays = logic.listOf(Collections.emptyList(), Birthday.class);

        assertEquals(0, appointments.size());
        assertEquals(1, birthdays.size());

    }

    @Test
    public void test002(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday birthday =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Collections.singletonList(birthday));

        List<Appointment> appointments = logic.listOf(Collections.emptyList(), Appointment.class);
        List<Birthday> birthdays = logic.listOf(
                Collections.singletonList(
                        new Filter<>("birthdayPerson", OperatorType.Equal, "You", String.class, AttributeFilterType.And)
                ), Birthday.class);

        assertEquals(0, appointments.size());
        assertEquals(1, birthdays.size());

    }

    @Test
    public void test003(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday birthday =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Collections.singletonList(birthday));

        List<Appointment> appointments = logic.listOf(Collections.emptyList(), Appointment.class);
        List<Birthday> birthdays = logic.listOf(
                Collections.singletonList(
                        new Filter<>("day", OperatorType.Equal, 11,Integer.class, AttributeFilterType.And)
                ), Birthday.class);

        assertEquals(0, appointments.size());
        assertEquals(0, birthdays.size());

    }

    @Test
    public void test004(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday birthday =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Collections.singletonList(birthday));
        List<Birthday> birthdays =
                logic.listOf(Collections.emptyList(), Birthday.class);

        assertEquals(1, birthdays.size());
        assertEquals(12, birthdays.get(0).getEventDate().getDayOfMonth());

        List<Appointment> appointments = logic.listOf(Collections.emptyList(), Appointment.class);
        logic.updateAllEvents(
                Collections.singletonList(
                  new Attribute("day", 20)
                ),
                Collections.singletonList(
                        new Filter<>("day", OperatorType.Equal, 12,Integer.class, AttributeFilterType.And)
                ));

        birthdays =
                logic.listOf(Collections.emptyList(), Birthday.class);

        assertEquals(0, appointments.size());
        assertEquals(1, birthdays.size());
        assertEquals(20, birthday.getEventDate().getDayOfMonth());

    }

    @Test
    public void test005(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday b0 =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");
        Birthday b1 =
                new Birthday(LocalDate.of(2020, Month.JULY, 7),
                        "Desc",
                        "You",
                        "Gift");

        Birthday b2 =
                new Birthday(LocalDate.of(2020, Month.JANUARY, 19),
                        "Desc",
                        "You",
                        "Gift");

        Birthday b3 =
                new Birthday(LocalDate.of(2020, Month.NOVEMBER, 22),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Arrays.asList(b0, b1, b2, b3));

        List<Event> events =
                logic.getAllEvents(Arrays.asList(
                        new Filter<>("day", OperatorType.LessEqual, 20,Integer.class, AttributeFilterType.And),
                        new Filter<>("month", OperatorType.LessEqual, Month.AUGUST.getValue(),Integer.class, AttributeFilterType.And),
                        new Filter<>("year", OperatorType.LessEqual, 2020,Integer.class, AttributeFilterType.And)
                ));

        assertEquals(3, events.size());



    }

    @Test
    public void test006(){
        BusinessLogic logic = new BusinessLogic();

        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Birthday b0 =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");
        Birthday b1 =
                new Birthday(LocalDate.of(2020, Month.JULY, 7),
                        "Desc",
                        "You",
                        "Gift");

        Birthday b2 =
                new Birthday(LocalDate.of(2020, Month.JANUARY, 19),
                        "Desc",
                        "You",
                        "Gift");

        Birthday b3 =
                new Birthday(LocalDate.of(2020, Month.NOVEMBER, 22),
                        "Desc",
                        "You",
                        "Gift");

        logic.addEvents(Arrays.asList(b0, b1, b2, b3));

        List<Event> events =
                logic.getAllEvents(Collections.singletonList(
                        new Filter<>(
                                "eventDate",
                                OperatorType.Less,
                                LocalDate.of(2020, Month.JULY, 7),
                                LocalDate.class,
                                AttributeFilterType.And)
                ));

        assertEquals(2, events.size());



    }

}