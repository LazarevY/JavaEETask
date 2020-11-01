package ui.console.commands;

import console.io.*;
import core.annotations.InjectMapClassKeyByEntries;
import core.annotations.MapKeyClassEntry;
import core.annotations.Singleton;
import logic.events.Appointment;
import logic.events.Birthday;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class Input implements Command {

    @InjectMapClassKeyByEntries({
            @MapKeyClassEntry(key = Birthday.class, implClass = BirthdayReader.class),
            @MapKeyClassEntry(key = Appointment.class, implClass = AppointmentReader.class),
            @MapKeyClassEntry(key = LocalDate.class, implClass = LocalDateReader.class),
            @MapKeyClassEntry(key = LocalTime.class, implClass = LocalTimeReader.class)
    })
    private Map<Class<?>, ConsoleClassReader<?>> readerMap;

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        ExecuteResult res = new ExecuteResult();
        res.addReturnParameter("input", readerMap.get(args.get("type")).
                safeRead((String) args.getOrDefault("msg", "")));
        return res;
    }
}
