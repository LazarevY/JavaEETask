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
            "c: Cancel\n";

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

        String choose = InputManager.getInstance().getStringFromStandardInput(CHOOSE_MSG);

        if (!choose.equals("c")){
            commandMap.get(choose).execute(args);
        }

        return ExecuteResult.emptySuccessResult();
    }
}
