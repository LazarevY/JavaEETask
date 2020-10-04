package ui.console.commands;

import logic.business.BusinessLogic;
import logic.events.Event;

import java.util.Collections;
import java.util.Map;

public class ViewAllEvents implements Command {

    public static final String CHOOSE_MSG = "Choose action:\n" +
            //"f: Add filter\n" +
            "s: Choose sort parameter(year is default)\n" +
            "v: View\n" +
            //"c: Clean all filters\n" +
            "q: Cancel\n";

    private final BusinessLogic logic;

    public ViewAllEvents(BusinessLogic logic) {
        this.logic = logic;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        System.out.println("====== View all events ======");

        for (Event e : logic.getAllEvents(Collections.emptyList()))
            System.out.println(e.shortDescription());

        //System.out.println(CHOOSE_MSG);
        System.out.println("=============================");

        return ExecuteResult.emptySuccessResult();

    }
}