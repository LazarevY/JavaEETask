package ui.console.commands;

import console.io.InputManager;
import core.annotations.InjectByType;
import data.AttributeFilterType;
import data.Filter;
import logic.business.BusinessLogic;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DeleteAction implements Command {

    @InjectByType
    private BusinessLogic logic;

    public static final String CHOOSE_MSG = "(Delete action)Input event id";


    @Override
    public ExecuteResult execute(Map<String, Object> args) {

        Command.printTemplate("Delete Action", "Enter a event id to delete it.");

        Integer id = InputManager.getInstance().getIntFromStandardInput(CHOOSE_MSG);

        List<Event> events;

        while ((events =  logic.getAllEvents(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And))))
                .size() != 1
        ){
            id = InputManager.getInstance()
                    .getIntFromStandardInput(
                            String.format("No event founded by id %d. Input id again", id)
                    );
        }

        Command.printTemplate("Delete Action",
                String.format("Event deleted: %s", events.get(0).fullDescription()));

        logic.removeEvents(
                Collections.singletonList(new Filter<>("id",OperatorType.Equal, id, Integer.class, AttributeFilterType.And))
        );

        return ExecuteResult.emptySuccessResult();

    }
}
