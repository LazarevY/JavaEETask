package logic.business;

import core.Application;
import core.ApplicationContext;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.dao.DAO;
import data.dao.HashMapDao;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class BusinessLogicTest {

    @Test
    public void test000(){
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

        Birthday birthday =
                new Birthday(LocalDate.of(2020, Month.APRIL, 12),
                        "Desc",
                        "You",
                        "Gift");
        logic.addEvents(Collections.singletonList(birthday));

        List<Event> birthdays = logic.getAllEvents(Collections.singletonList(
                new Filter<>("day", OperatorType.More, 0, AttributeFilterType.Enough)
        ));

        assertEquals(1, birthdays.size());

    }

    @Test
    public void test001(){
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

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
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

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
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);
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
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

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
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

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
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

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

    @Test
    public void test007(){
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

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

        Appointment a1 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 30),
                        "Desc",
                        "Person",
                        LocalTime.of(18,0,0,0));

        logic.addEvents(Arrays.asList(b0, b1, b2, b3));
        logic.addEvents(Collections.singletonList(a1));

        List<Event> events =
                logic.getAllEvents(Collections.singletonList(
                        new Filter<>(
                                "eventDate",
                                OperatorType.Less,
                                LocalDate.of(2020, Month.JULY, 7),
                                LocalDate.class,
                                AttributeFilterType.And)
                ));

        assertEquals(3, events.size());

    }

    @Test
    public void test008(){
        ApplicationContext applicationContext = Application.run("",
                new HashMap<>(Map.of(DAO.class, HashMapDao.class)));


        BusinessLogic logic = applicationContext.getObject(BusinessLogic.class);

        Appointment a1 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 30),
                        "Desc",
                        "Person",
                        LocalTime.of(18,0));
        Appointment a2 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 31),
                        "Desc",
                        "Person",
                        LocalTime.of(12, 30));
        Appointment a3 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 30),
                        "Desc",
                        "Person",
                        LocalTime.of(14,15));
        Appointment a4 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 27),
                        "Desc",
                        "Person",
                        LocalTime.of(17, 0));

        logic.addEvents(Arrays.asList(a1, a2, a3, a4));

        List<Event> events =
                logic.getAllEvents(Arrays.asList(
                        new Filter<>(
                                "eventDate",
                                OperatorType.Less,
                                LocalDate.of(2020, Month.JULY, 7),
                                LocalDate.class,
                                AttributeFilterType.Enough),
                        new Filter<>("appointmentTime",
                                OperatorType.LessEqual,
                                LocalTime.of(18,0),
                                LocalTime.class,
                                AttributeFilterType.And)
                ));

        assertEquals(4, events.size());

    }

}