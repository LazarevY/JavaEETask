package ui.console.commands;

import java.util.Map;

public interface Command {

    ExecuteResult execute(Map<String, Object> args);

    default void registerSubCommand (String name, Command command){

    }

    static void printTemplate(String header, String body){
        String headerH = String.format("====== %s ======", header);
        System.out.println(headerH);
        System.out.println(body);
        System.out.println("=".repeat(headerH.length()));
    }

}
