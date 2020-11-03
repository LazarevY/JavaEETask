package ui.console;

import core.inverseofcontrol.annotations.InjectByType;
import ui.UI;
import ui.console.commands.BaseActionChoose;

import java.util.HashMap;

public class ConsoleUI implements UI {

    @InjectByType
    private BaseActionChoose choose;

    @Override
    public void show() {
        choose.execute(new HashMap<>());
    }
}
