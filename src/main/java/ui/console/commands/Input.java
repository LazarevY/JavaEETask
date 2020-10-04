package ui.console.commands;

import console.io.AppointmentReader;
import console.io.BirthdayReader;
import console.io.ConsoleClassReader;
import logic.events.Appointment;
import logic.events.Birthday;

import java.util.HashMap;
import java.util.Map;

public class Input implements Command {

    private final Map<Class<?>, ConsoleClassReader<?>> readerMap;
    private static Input instance;

    private Input(){
        readerMap = new HashMap<Class<?>, ConsoleClassReader<?>>(){{
           put(Birthday.class, new BirthdayReader());
           put(Appointment.class, new AppointmentReader());
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
        res.addReturnParameter("input", readerMap.get(args.get("type")).safeRead());
        return res;
    }
}
