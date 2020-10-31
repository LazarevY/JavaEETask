package ui.console.commands;

import console.io.InputManager;
import core.annotations.InjectMapByEntries;
import core.annotations.MapEntry;
import logic.business.BusinessLogic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseActionChoose implements Command {

    @InjectMapByEntries({
            @MapEntry(key = "v", implClass = ViewAction.class),
            @MapEntry(key = "a", implClass = AddAction.class),
            @MapEntry(key = "e", implClass = EditAction.class),
            @MapEntry(key = "d", implClass = DeleteAction.class)})
    private Map<String, Command> commandMap;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            "v: View events\n" +
            "a: Add events\n" +
            "e: Edit event\n" +
            "d: Delete events\n" +
            "q: Quit";


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Choose Action", CHOOSE_MSG);
        String choose = InputManager.getInstance().getStringFromStandardInput("(Choose Action) Input action");
        while (!choose.equals("q")) {
            if (!commandMap.containsKey(choose)) {
                System.out.println("Wrong action. Try again\n");
                choose = InputManager.getInstance().getStringFromStandardInput("(Choose Action) Input action");
            }
            else {
                commandMap.get(choose).execute(new HashMap<>());
                Command.printTemplate("Choose action", CHOOSE_MSG);
                choose = InputManager.getInstance().getStringFromStandardInput("(Choose Action) Input command");
            }
        }
        return ExecuteResult.emptySuccessResult();
    }
}
