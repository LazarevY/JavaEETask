package ui.console.commands;

import console.io.*;
import logic.events.Appointment;
import logic.events.Birthday;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Input implements Command {

    private final Map<Class<?>, ConsoleClassReader<?>> readerMap;
    private static Input instance;

    private Input(){
        readerMap = new HashMap<Class<?>, ConsoleClassReader<?>>(){{
           put(Birthday.class, new BirthdayReader());
           put(Appointment.class, new AppointmentReader());
           put(LocalDate.class, new LocalDateReader());
           put(LocalTime.class, new LocalTimeReader());
        }};
    }

    public static Input getInstance(){
        if (instance == null)
            instance = new Input();
        return instance;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        ExecuteResult res = new ExecuteResult();
        res.addReturnParameter("input", readerMap.get(args.get("type")).
                safeRead((String) args.getOrDefault("msg", "")));
        return res;
    }
}
