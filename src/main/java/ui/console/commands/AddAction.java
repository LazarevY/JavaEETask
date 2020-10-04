package ui.console.commands;

import console.io.BirthdayReader;
import console.io.InputManager;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddAction implements Command {

    private final BusinessLogic logic;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            "b: Add new birthday event\n" +
            "a: Add new appointment event\n" +
            "q: Return back";

    public AddAction(BusinessLogic logic) {
        this.logic = logic;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Add Action", CHOOSE_MSG);

        String command = InputManager.getInstance().getStringFromStandardInput("Input action");

        while (!command.equals("q")){

            if (command.equals("b")){
                Birthday birthday = (Birthday) Input
                        .getInstance()
                        .execute(Map.of("type", Birthday.class))
                        .getReturnMap()
                        .get("input");
                logic.addEvents(Collections.singletonList(birthday));
                Command.printTemplate("Add action",
                        String.format("Event added: %s", birthday.fullDescription()));
            }
            else if (command.equals("a")){
                Appointment appointment = (Appointment) Input
                        .getInstance()
                        .execute(Map.of("type", Appointment.class))
                        .getReturnMap()
                        .get("input");
                logic.addEvents(Collections.singletonList(appointment));
                Command.printTemplate("Add action",
                        String.format("Event added: %s", appointment.fullDescription()));
            }
            else {
                System.out.println("Wrong action. Try again.");
            }

            command = InputManager.getInstance().getStringFromStandardInput("(Add action) Input action");
        }

        return ExecuteResult.emptySuccessResult();

    }
}
