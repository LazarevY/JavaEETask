package ui.console.commands;

import core.annotations.InjectByType;
import logic.business.BusinessLogic;
import logic.events.Birthday;
import logic.events.Event;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class ViewBirthdays implements Command {

    @InjectByType
    private  BusinessLogic logic;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            //"f: Add filter\n" +
            "s: Choose sort parameter(year is default)\n" +
            "v: View\n" +
            //"c: Clean all filters\n" +
            "q: Cancel\n";

    @Override
    @SuppressWarnings("unchecked")
    public ExecuteResult execute(Map<String, Object> args) {
        System.out.println("====== View birthday events ======");

        for (Birthday e: logic.listOf(Collections.emptyList(), Birthday.class,
                (Comparator<Birthday>)args.get("sort")))
            System.out.println(args.get("desc").equals("f") ? e.fullDescription() : e.shortDescription());

        //System.out.println(CHOOSE_MSG);
        System.out.println("=============================");

        return ExecuteResult.emptySuccessResult();
    }
}
