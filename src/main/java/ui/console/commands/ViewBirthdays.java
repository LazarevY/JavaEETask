package ui.console.commands;

import logic.business.BusinessLogic;
import logic.events.Birthday;
import logic.events.Event;

import java.util.Collections;
import java.util.Map;

public class ViewBirthdays implements Command {

    private final BusinessLogic logic;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            //"f: Add filter\n" +
            "s: Choose sort parameter(year is default)\n" +
            "v: View\n" +
            //"c: Clean all filters\n" +
            "q: Cancel\n";

    public ViewBirthdays(BusinessLogic logic) {
        this.logic = logic;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        System.out.println("\n====== View birthday events ======\n");

        for (Birthday e: logic.listOf(Collections.emptyList(), Birthday.class))
            System.out.println(e.shortDescription());

        //System.out.println(CHOOSE_MSG);
        System.out.println("\n=============================\n");

        return ExecuteResult.emptySuccessResult();
    }
}
