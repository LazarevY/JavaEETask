package ui.console.commands;

import console.io.InputManager;
import core.inverseofcontrol.annotations.InjectByType;
import data.AttributeFilterType;
import data.Filter;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DeleteAction implements Command {

    public static final String CHOOSE_MSG = "(Delete action)Input event id";
    @InjectByType
    private BusinessLogic logic;
    @InjectByType
    private InputManager inputManager;


    @Override
    public ExecuteResult execute(Map<String, Object> args) {

        Command.printTemplate("Delete Action", "Enter a event id to delete it.");

        Class<? extends Event> eventClass = null;

        String st = inputManager.getStringFromStandardInput("Choose type of event: b - Birthday, a - Appointment");
        while (eventClass == null) {

            if (st.equals("b")) {
                eventClass = Birthday.class;
                break;
            } else if (st.equals("a")) {
                eventClass = Appointment.class;
                break;
            }

            st = inputManager.getStringFromStandardInput("Wrong.Choose type of event: b - Birthday, a - Appointment");

        }

        Integer id = inputManager.getIntFromStandardInput(CHOOSE_MSG);

        List<? extends Event> events;

        while ((events = logic.listOf(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And)), eventClass))
                .size() != 1
        ) {
            id = inputManager
                    .getIntFromStandardInput(
                            String.format("No event founded by id %d. Input id again", id)
                    );
        }

        Command.printTemplate("Delete Action",
                String.format("Event deleted: %s", events.get(0).fullDescription()));

        logic.removeEventsType(
                Collections.singletonList(new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And)),
                eventClass
        );

        return ExecuteResult.emptySuccessResult();

    }
}
