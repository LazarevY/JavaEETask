package ui.console.commands;

import console.io.InputManager;
import logic.business.BusinessLogic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseActionChoose implements Command{

    private final Map<String, Command> commandMap;

    public BaseActionChoose(BusinessLogic logic){
        commandMap = new HashMap<String, Command>(){{
            put("v", new ViewAction(logic));
            put("d", new DeleteAction(logic));
            put("a", new AddAction(logic));
        }};
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
            String command = InputManager.getInstance().getStringFromStandardInput("Choose action:\n" +
                    "v: View events\n" +
                    "a: Add events\n" +
                    "e: Edit event\n" +
                    "d: Delete events\n" +
                    "q: Quit\n");
            while (!command.equals("exit")){
                if (!commandMap.containsKey(command))
                    System.out.println("Unknow command");
                else
                    commandMap.get(command).execute(Collections.emptyMap());
                command = InputManager.getInstance().getStringFromStandardInput("Input command");
            }
            return ExecuteResult.emptySuccessResult();
    }
}
