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
            put("view", new ViewAction(logic));
        }};
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
            String command = InputManager.getInstance().getStringFromStandardInput("Input command");
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
