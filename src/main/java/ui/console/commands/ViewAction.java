package ui.console.commands;

import console.io.InputManager;
import logic.business.BusinessLogic;
import logic.events.Event;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAction implements Command {

    private final BusinessLogic businessLogic;
    private final Map<String, Command> commandMap;

    public static final String CHOOSE_MSG = "What I must do?\n" +
            "a: View add events\n" +
            "b: View only birthdays\n" +
            "t: View only appointments\n" +
            "q: Return back";

    public ViewAction(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
        this.commandMap = new HashMap<String, Command>(){{
            put("a", new ViewAllEvents(businessLogic));
            put("b", new ViewBirthdays(businessLogic));
            put("t", new ViewAppointments(businessLogic));
        }};
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {

        Command.printTemplate("View Action", CHOOSE_MSG);
        String choose = InputManager.getInstance().getStringFromStandardInput("(View Action) Input action");

        while (!choose.equals("q")){

            if (!commandMap.containsKey(choose)){
                System.out.println("Wrong action. Try again\n");
            }
            else {
                commandMap.get(choose).execute(args);
                Command.printTemplate("View Action", CHOOSE_MSG);
            }
            choose = InputManager.getInstance().getStringFromStandardInput("(View Action) Input action");

        }


        return ExecuteResult.emptySuccessResult();
    }
}
