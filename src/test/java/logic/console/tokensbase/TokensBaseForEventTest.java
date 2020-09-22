package logic.console.tokensbase;

import logic.events.AppointmentEvent;
import logic.events.Birthday;
import logic.tokens.ariphmetic.*;
import logic.tokens.base.BooleanToken;
import org.junit.Test;
import utils.calendar.CalendarUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class TokensBaseForEventTest {

    @Test
    public void test000(){
        BooleanToken nameEquals = new EqualToken();
        BooleanToken yearLess = new LessToken();

        nameEquals.setOperand("right", "Name");
        NodeTokenDescription forName = new NodeTokenDescription(nameEquals, "left");

        yearLess.setOperand("right", 2020);
        NodeTokenDescription forYear = new NodeTokenDescription(yearLess, "left");

        TokensBaseBirthdayEvent tree = new TokensBaseBirthdayEvent();

        tree.insertNode("person", forName);
        tree.insertNode("year", forYear);

        Birthday e = new Birthday(
                LocalDate.of(2019, 5, 7),
                "Event",
                "Name",
                "Void");

        tree.acceptEvent(e);

        assertTrue(nameEquals.evaluate());
        assertTrue(yearLess.evaluate());

    }

    @Test
    public void test001(){
        BooleanToken nameEquals = new EqualToken();
        BooleanToken yearLess = new LessToken();
        BooleanToken yearMore = new MoreToken();
        BooleanToken dayNotEqual = new NotEqualToken();

        nameEquals.setOperand("right", "Name");
        NodeTokenDescription forName = new NodeTokenDescription(nameEquals, "left");

        yearLess.setOperand("right", 2024);
        NodeTokenDescription forYear = new NodeTokenDescription(yearLess, "left");

        yearMore.setOperand("right", 2018);
        NodeTokenDescription forYearMore = new NodeTokenDescription(yearMore, "left");

        dayNotEqual.setOperand("right", 21);
        NodeTokenDescription forDay = new NodeTokenDescription(dayNotEqual, "left");

        TokensBaseBirthdayEvent tree = new TokensBaseBirthdayEvent();

        tree.insertNode("person", forName);
        tree.insertNode("year", forYear);
        tree.insertNode("year", forYearMore);
        tree.insertNode("day", forDay);

        Birthday e = new Birthday(
                LocalDate.of(2019, 5, 7),
                "Event",
                "Name",
                "Void");

        tree.acceptEvent(e);

        assertTrue(nameEquals.evaluate());
        assertTrue(yearLess.evaluate());
        assertTrue(yearMore.evaluate());
        assertTrue(dayNotEqual.evaluate());

    }

    @Test
    public void test002(){
        BooleanToken nameEquals = new EqualToken();
        BooleanToken yearLess = new LessToken();
        BooleanToken yearMore = new MoreToken();
        BooleanToken dayNotEqual = new NotEqualToken();

        nameEquals.setOperand("right", "Name");
        NodeTokenDescription forName = new NodeTokenDescription(nameEquals, "left");

        yearLess.setOperand("right", 2024);
        NodeTokenDescription forYear = new NodeTokenDescription(yearLess, "left");

        yearMore.setOperand("right", 2018);
        NodeTokenDescription forYearMore = new NodeTokenDescription(yearMore, "left");

        dayNotEqual.setOperand("right", 7);
        NodeTokenDescription forDay = new NodeTokenDescription(dayNotEqual, "left");

        TokensBaseBirthdayEvent tree = new TokensBaseBirthdayEvent();

        tree.insertNode("person", forName);
        tree.insertNode("year", forYear);
        tree.insertNode("year", forYearMore);
        tree.insertNode("day", forDay);

        Birthday e = new Birthday(
                LocalDate.of(2019, 5, 7),
                "Event",
                "Name",
                "Void");

        tree.acceptEvent(e);

        assertTrue(nameEquals.evaluate());
        assertTrue(yearLess.evaluate());
        assertTrue(yearMore.evaluate());
        assertFalse(dayNotEqual.evaluate());

    }

    @Test
    public void test003(){
        BooleanToken nameEquals = new EqualToken();
        BooleanToken yearLess = new LessToken();
        BooleanToken yearMore = new MoreToken();
        BooleanToken dayNotEqual = new NotEqualToken();
        BooleanToken hourMore = new MoreToken();
        BooleanToken minutesEqual = new EqualToken();

        nameEquals.setOperand("right", "Name");
        NodeTokenDescription forName = new NodeTokenDescription(nameEquals, "left");

        yearLess.setOperand("right", 2024);
        NodeTokenDescription forYear = new NodeTokenDescription(yearLess, "left");

        yearMore.setOperand("right", 2018);
        NodeTokenDescription forYearMore = new NodeTokenDescription(yearMore, "left");

        dayNotEqual.setOperand("right", 7);
        NodeTokenDescription forDay = new NodeTokenDescription(dayNotEqual, "left");

        hourMore.setOperand("right", 13);
        NodeTokenDescription forHourMore = new NodeTokenDescription(hourMore, "left");


        TokensBaseAppointmentEvent tree = new TokensBaseAppointmentEvent();

        tree.insertNode("person", forName);
        tree.insertNode("year", forYear);
        tree.insertNode("year", forYearMore);
        tree.insertNode("day", forDay);

        AppointmentEvent e = new AppointmentEvent(
                LocalDate.of(2019, 5, 7),
                "Event",
                "Name",
               LocalTime.of(14, 30, 0,0));

        tree.acceptEvent(e);

        assertTrue(nameEquals.evaluate());
        assertTrue(yearLess.evaluate());
        assertTrue(yearMore.evaluate());
        assertFalse(dayNotEqual.evaluate());

    }

}