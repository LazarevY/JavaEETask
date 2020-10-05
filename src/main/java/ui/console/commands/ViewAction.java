package ui.console.commands;

import console.io.InputManager;
import logic.business.BusinessLogic;
import logic.events.Event;

import java.util.*;

public class ViewAction implements Command {

    private final BusinessLogic businessLogic;
    private final Map<String, Command> commandMap;

    public static final String CHOOSE_MSG = "What I must do?\n" +
            "s: Select sort parameter\n" +
            "a: View add events\n" +
            "b: View only birthdays\n" +
            "t: View only appointments\n" +
            "f: View with full description\n" +
            "d: View with short description\n" +
            "q: Return back";

    public ViewAction(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
        this.commandMap = new HashMap<String, Command>() {{
            put("a", new ViewAllEvents(businessLogic));
            put("b", new ViewBirthdays(businessLogic));
            put("t", new ViewAppointments(businessLogic));
            put("h", new SelectSortParameter());
        }};
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {

        Command.printTemplate("View Action", CHOOSE_MSG);
        String choose = InputManager.getInstance().getStringFromStandardInput("(View Action) Input action");

        Comparator<Event> c = Comparator.comparingInt(event -> event.getEventDate().getYear());

        HashMap<String, Object> argsMap = new HashMap<>();
        argsMap.put("sort", c);
        argsMap.put("desc", "d");

        while (!choose.equals("q")) {
            ExecuteResult res = new ExecuteResult();

            if (choose.equals("f") || choose.equals("d")) {
                argsMap.put("desc", choose);
                choose = InputManager.getInstance().getStringFromStandardInput("(View Action) Input action");
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

            choose = InputManager.getInstance().getStringFromStandardInput("(View Action) Input action");

        }


        return ExecuteResult.emptySuccessResult();
    }
}
