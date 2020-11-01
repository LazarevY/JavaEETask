package ui.console.commands;

import console.io.BirthdayReader;
import console.io.InputManager;
import core.annotations.InjectByType;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddAction implements Command {

    @InjectByType
    private BusinessLogic logic;

    @InjectByType
    private Input input;

    public static final String CHOOSE_MSG = "Choose action:\n" +
            "b: Add new birthday event\n" +
            "a: Add new appointment event\n" +
            "q: Return back";

    @InjectByType
    private InputManager inputManager;


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Add Action", CHOOSE_MSG);

        String command = inputManager.getStringFromStandardInput("Input action");

        while (!command.equals("q")){

            if (command.equals("b")){
                Birthday birthday = (Birthday) input
                        .execute(Map.of("type", Birthday.class))
                        .getReturnMap()
                        .get("input");
                logic.addEvents(Collections.singletonList(birthday));
                Command.printTemplate("Add action",
                        String.format("Event added: %s", birthday.fullDescription()));
            }
            else if (command.equals("a")){
                Appointment appointment = (Appointment) input
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

            command = inputManager.getStringFromStandardInput("(Add action) Input action");
        }

        return ExecuteResult.emptySuccessResult();

    }
}
