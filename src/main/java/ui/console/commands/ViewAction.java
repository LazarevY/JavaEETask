package ui.console.commands;

import console.io.InputManager;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.InjectMapStringKeyByEntries;
import core.inverseofcontrol.annotations.MapStringKeyEntry;
import logic.events.Event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ViewAction implements Command {

    public static final String CHOOSE_MSG = "What I must do?\n" +
            "s: Select sort parameter\n" +
            "a: View add events\n" +
            "b: View only birthdays\n" +
            "t: View only appointments\n" +
            "f: View with full description\n" +
            "d: View with short description\n" +
            "q: Return back";
    @InjectMapStringKeyByEntries({
            @MapStringKeyEntry(key = "a", implClass = ViewAllEvents.class),
            @MapStringKeyEntry(key = "b", implClass = ViewBirthdays.class),
            @MapStringKeyEntry(key = "t", implClass = ViewAppointments.class),
            @MapStringKeyEntry(key = "s", implClass = SelectSortParameter.class)
    })
    private Map<String, Command> commandMap;
    @InjectByType
    private InputManager inputManager;

    @Override
    public ExecuteResult execute(Map<String, Object> args) {

        Command.printTemplate("View Action", CHOOSE_MSG);
        String choose = inputManager.getStringFromStandardInput("(View Action) Input action");

        Comparator<Event> c = Comparator.comparingInt(event -> event.getEventDate().getYear());

        HashMap<String, Object> argsMap = new HashMap<>();
        argsMap.put("sort", c);
        argsMap.put("desc", "d");

        while (!choose.equals("q")) {
            ExecuteResult res = new ExecuteResult();

            if (choose.equals("f") || choose.equals("d")) {
                argsMap.put("desc", choose);
                choose = inputManager.getStringFromStandardInput("(View Action) Input action");
                continue;
            }

            if (!commandMap.containsKey(choose)) {
                System.out.println("Wrong action. Try again\n");
            } else {
                res = commandMap.get(choose).execute(argsMap);
                Command.printTemplate("View Action", CHOOSE_MSG);

            }

            if (choose.equals("s")) {
                argsMap.put("sort", res.getReturnMap().get("sort"));
            }

            choose = inputManager.getStringFromStandardInput("(View Action) Input action");

        }


        return ExecuteResult.emptySuccessResult();
    }
}
