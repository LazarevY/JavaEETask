package ui.console.commands;

import console.io.InputManager;
import core.annotations.InjectByType;
import core.annotations.InjectMapStringKeyByEntries;
import core.annotations.MapStringKeyEntry;

import java.util.HashMap;
import java.util.Map;

public class BaseActionChoose implements Command {

    @InjectMapStringKeyByEntries({
            @MapStringKeyEntry(key = "v", implClass = ViewAction.class),
            @MapStringKeyEntry(key = "a", implClass = AddAction.class),
            @MapStringKeyEntry(key = "e", implClass = EditAction.class),
            @MapStringKeyEntry(key = "d", implClass = DeleteAction.class)})
    private Map<String, Command> commandMap;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            "v: View events\n" +
            "a: Add events\n" +
            "e: Edit event\n" +
            "d: Delete events\n" +
            "q: Quit";

    @InjectByType
    private InputManager inputManager;


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Choose Action", CHOOSE_MSG);
        String choose = inputManager.getStringFromStandardInput("(Choose Action) Input action");
        while (!choose.equals("q")) {
            if (!commandMap.containsKey(choose)) {
                System.out.println("Wrong action. Try again\n");
                choose = inputManager.getStringFromStandardInput("(Choose Action) Input action");
            }
            else {
                commandMap.get(choose).execute(new HashMap<>());
                Command.printTemplate("Choose action", CHOOSE_MSG);
                choose = inputManager.getStringFromStandardInput("(Choose Action) Input command");
            }
        }
        return ExecuteResult.emptySuccessResult();
    }
}
