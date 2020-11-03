package ui.console.commands;

import console.io.InputManager;
import core.inverseofcontrol.annotations.InjectByType;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import logic.business.BusinessLogic;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EditEventAction implements Command {

    public static final String CHOOSE_MSG = "Choose param for edit:\n" +
            "d: Date of event\n" +
            "e: Description of event\n" +
            "q: Return back";
    @InjectByType
    private BusinessLogic logic;
    @InjectByType
    private Input input;
    @InjectByType
    private InputManager inputManager;


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Event edit action", CHOOSE_MSG);

        String command = inputManager.getStringFromStandardInput("(Edit event action) Choose param");

        Attribute attribute;

        while (!command.equals("q")) {


            if (command.equals("d")) {

                LocalDate date =
                        (LocalDate) input
                                .execute(Map.of("type", LocalDate.class, "msg", "Input date"))
                                .getReturnMap().get("input");

                attribute = new Attribute("eventDate",
                        date);
            } else if (command.equals("e")) {
                attribute = new Attribute("eventDescription",
                        inputManager.getStringFromStandardInput("Type new description"));
            } else {
                command = inputManager.getStringFromStandardInput("Wrong. Input again");
                continue;
            }


            edit(getIdFilter(), attribute);
            command = inputManager.getStringFromStandardInput("(Edit event action) Choose param");
        }

        return ExecuteResult.emptySuccessResult();
    }

    private void edit(Filter<Integer> filter, Attribute attribute) {
        logic.updateAllEvents(
                Collections.singletonList(attribute),
                Collections.singletonList(filter)
        );
    }

    private Filter<Integer> getIdFilter() {
        Integer id = inputManager.getIntFromStandardInput("Input id of event");

        List<Event> events;

        while ((events = logic.getAllEvents(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And))))
                .size() != 1
        ) {
            id = inputManager
                    .getIntFromStandardInput(
                            String.format("No event founded by id %d. Input id again", id)
                    );
        }

        return new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And);

    }
}
