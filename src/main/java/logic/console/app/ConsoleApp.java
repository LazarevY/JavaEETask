package logic.console.app;

import database.emulation.DataBaseEmulation;
import logic.console.parsing.FilterStringParser;
import logic.console.tokensbase.TokensBaseAppointmentEvent;
import logic.console.tokensbase.TokensBaseBirthdayEvent;
import logic.events.Event;
import logic.events.Birthday;
import org.apache.tools.ant.types.Commandline;
import utils.calendar.CalendarUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ConsoleApp {

    public ConsoleApp(){
        dataBase = new DataBaseEmulation();

        dataBase.insertEvent(new Birthday(
                LocalDate.of(2020, 11, 20),
                "None",
                "Person",
                "Node"
        ));
    }

    public void execCommand(String command) {
        String []args = Commandline.translateCommandline(command);
        if (args[0].equals("view")){
            execView(Arrays.copyOfRange(args, 1, args.length));
        }
    }

    private void execView(String[] args) {
        List<Predicate<Event>> predicates = new ArrayList<>();

        if (args[0].equals("birthday") || args[0].equals("all")) {
            TokensBaseBirthdayEvent base = new TokensBaseBirthdayEvent();
            Predicate<Event> p = FilterStringParser.parsePredicate(args[1], base);
            if (p == null) {
                System.out.println("Illegal filter arguments");
                return;
            }
            predicates.add(p);
        } else if (args[0].equals("appointment") || args[0].equals("all")) {
            TokensBaseAppointmentEvent base = new TokensBaseAppointmentEvent();
            Predicate<Event> p = FilterStringParser.parsePredicate(args[1], base);
            if (p == null) {
                System.out.println("Illegal filter arguments");
                return;
            }
            predicates.add(p);
        } else {
            System.out.println("Illegal event type");
        }

        List<Event> events = new ArrayList<>();

        for (Predicate<Event> p: predicates)
            events.addAll(dataBase.eventsForPredicate(p));

        for (Event e: events)
            System.out.println(e.shortDescription());

    }

    private void execAdd() {

    }

    private void execEdit() {

    }

    private void execDelete() {

    }

    private DataBaseEmulation dataBase;
}
