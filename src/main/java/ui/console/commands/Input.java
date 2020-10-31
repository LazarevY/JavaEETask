package ui.console.commands;

import console.io.*;
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

    private Map<Class<?>, ConsoleClassReader<?>> readerMap;

    @PostConstruct
    private void init(){
        readerMap = new HashMap<Class<?>, ConsoleClassReader<?>>(){{
           put(Birthday.class, new BirthdayReader());
           put(Appointment.class, new AppointmentReader());
           put(LocalDate.class, new LocalDateReader());
           put(LocalTime.class, new LocalTimeReader());
        }};
    }


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        ExecuteResult res = new ExecuteResult();
        res.addReturnParameter("input", readerMap.get(args.get("type")).
                safeRead((String) args.getOrDefault("msg", "")));
        return res;
    }
}
