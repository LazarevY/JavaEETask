package ui.console.commands;

import console.io.InputManager;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.InjectMapStringKeyByEntries;
import core.inverseofcontrol.annotations.MapStringKeyEntry;

import java.util.HashMap;
import java.util.Map;

public class EditAction implements Command {

    public static final String CHOOSE_MSG = "Choose action:\n" +
            "b: Edit birthday event\n" +
            "t: Edit appointment event\n" +
            "q: Return back";
    @InjectMapStringKeyByEntries({
            @MapStringKeyEntry(key = "t", implClass = EditAppointmentAction.class),
            @MapStringKeyEntry(key = "b", implClass = EditBirthday.class)
    })
    private Map<String, Command> commandMap;
    @InjectByType
    private InputManager inputManager;


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Edit Action", CHOOSE_MSG);
        String choose = inputManager.getStringFromStandardInput("(Edit Action) Input action");
        while (!choose.equals("q")) {
            if (!commandMap.containsKey(choose)) {
                System.out.println("Wrong action. Try again\n");
            } else {
                commandMap.get(choose).execute(new HashMap<>());
                Command.printTemplate("View Action", CHOOSE_MSG);

            }
            choose = inputManager.getStringFromStandardInput("(Edit Action) Input action");

        }

        return ExecuteResult.emptySuccessResult();
    }
}
