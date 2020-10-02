package ui.console.commands;

import console.io.BirthdayReader;

import java.util.Map;

public class InputCommand implements Command {
    @Override
    public int execute(Map<String, Object> args) {
        System.out.println(new BirthdayReader().safeRead().toString());
        return 0;
    }
}
