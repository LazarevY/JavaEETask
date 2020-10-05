package ui.console.commands;

import console.io.InputManager;
import logic.business.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

public class EditAction implements Command {
    private final BusinessLogic logic;

    private final Map<String, Command> commandMap;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            "a: Edit any event\n" +
            "b: Edit birthday event\n" +
            "t: Edit appointment event\n" +
            "q: Return back";

    public EditAction(BusinessLogic logic) {
        this.logic = logic;
        commandMap = new HashMap<String, Command>(){{
            put("a", new EditEventAction(logic));
            put("t", new EditAppointmentAction(logic));
        }};
    }


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Edit Action", CHOOSE_MSG);
        String choose = InputManager.getInstance().getStringFromStandardInput("(Edit Action) Input action");
        while (!choose.equals("q")){
            if (!commandMap.containsKey(choose)){
                System.out.println("Wrong action. Try again\n");
            }
            else {
                commandMap.get(choose).execute(new HashMap<>());
                Command.printTemplate("View Action", CHOOSE_MSG);

            }
            choose = InputManager.getInstance().getStringFromStandardInput("(Edit Action) Input action");

        }

        return ExecuteResult.emptySuccessResult();
    }
}
