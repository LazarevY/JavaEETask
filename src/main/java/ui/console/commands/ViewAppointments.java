package ui.console.commands;

import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;

import java.util.Collections;
import java.util.Map;

public class ViewAppointments implements Command {

    private final BusinessLogic logic;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            //"f: Add filter\n" +
            "s: Choose sort parameter(year is default)\n" +
            "v: View\n" +
            //"c: Clean all filters\n" +
            "q: Cancel\n";

    public ViewAppointments(BusinessLogic logic) {
        this.logic = logic;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        System.out.println("====== View appointment events ======");

        for (Appointment e: logic.listOf(Collections.emptyList(), Appointment.class))
            System.out.println(e.shortDescription());

        //System.out.println(CHOOSE_MSG);
        System.out.println("=============================");

        return ExecuteResult.emptySuccessResult();
    }
}
