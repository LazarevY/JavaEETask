package ui.console.commands;

import console.io.InputManager;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import logic.business.BusinessLogic;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditEventAction implements Command {

    private final BusinessLogic logic;

    public static final String CHOOSE_MSG = "Choose param for edit:\n" +
            "d: Date of event\n" +
            "e: Description of event\n" +
            "q: Return back";

    public EditEventAction(BusinessLogic logic) {
        this.logic = logic;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Event edit action", CHOOSE_MSG);

        String command = InputManager.getInstance().getStringFromStandardInput("(Edit event action) Choose param");

        Attribute attribute;

        while (!command.equals("q")) {


            if (command.equals("d")) {

                LocalDate date =
                        (LocalDate) Input.getInstance()
                                .execute(Map.of("type", LocalDate.class, "msg", "Input date"))
                                .getReturnMap().get("input");

                attribute = new Attribute("eventDate",
                                date);
            } else if (command.equals("e")) {
                attribute = new Attribute("eventDescription",
                                InputManager.getInstance().getStringFromStandardInput("Type new description"));
            } else {
                command = InputManager.getInstance().getStringFromStandardInput("Wrong. Input again");
                continue;
            }


            edit(getIdFilter(), attribute);
            command = InputManager.getInstance().getStringFromStandardInput("(Edit event action) Choose param");
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
        Integer id = InputManager.getInstance().getIntFromStandardInput("Input id of event");

        List<Event> events;

        while ((events = logic.getAllEvents(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And))))
                .size() != 1
        ) {
            id = InputManager.getInstance()
                    .getIntFromStandardInput(
                            String.format("No event founded by id %d. Input id again", id)
                    );
        }

        return new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And);

    }
}
