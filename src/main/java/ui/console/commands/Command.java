package ui.console.commands;

import java.util.Map;

public interface Command {

    ExecuteResult execute(Map<String, Object> args);

    default void registerSubCommand (String name, Command command){

    }

}
