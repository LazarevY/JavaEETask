package ui.console.commands;

import console.io.InputManager;
import core.annotations.InjectByType;
import core.annotations.InjectMapByEntries;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import logic.business.BusinessLogic;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EditBirthday implements Command{

    @InjectByType
    private BusinessLogic logic;

    @InjectByType
    private Input input;

    public static final String CHOOSE_MSG = "Choose param for edit:\n" +
            "d: Date of event\n" +
            "e: Description of event\n" +
            "p: Birthday person\n" +
            "g: Gift description\n" +
            "q: Return back";


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Birthday edit action", CHOOSE_MSG);

        String command = InputManager.getInstance().getStringFromStandardInput("(Birthday event action) Choose param");

        Attribute attribute;

        while (!command.equals("q")) {


            switch (command) {
                case "d":

                    LocalDate date =
                            (LocalDate) input
                                    .execute(Map.of("type", LocalDate.class, "msg", "Input date"))
                                    .getReturnMap().get("input");

                    attribute = new Attribute("eventDate",
                            date);
                    break;
                case "e":
                    attribute = new Attribute("eventDescription",
                            InputManager.getInstance().getStringFromStandardInput("Type new description"));

                    break;
                case "p":
                    attribute = new Attribute("birthdayPerson",
                            InputManager.getInstance().getStringFromStandardInput("Type new person"));
                    break;
                case "g":
                    attribute = new Attribute("birthdayGift",
                            InputManager.getInstance().getStringFromStandardInput("Type new gift"));
                    break;
                default:
                    command = InputManager.getInstance().getStringFromStandardInput("Wrong. Input again");
                    continue;
            }


            edit(getIdFilter(), attribute);
            command = InputManager.getInstance().getStringFromStandardInput("(Birthday event action) Choose param");
        }

        return ExecuteResult.emptySuccessResult();
    }

    private void edit(Filter<Integer> filter, Attribute attribute) {
        logic.updateEvents(
                Collections.singletonList(attribute),
                Collections.singletonList(filter),
                Birthday.class
        );
    }

    private Filter<Integer> getIdFilter() {
        Integer id = InputManager.getInstance().getIntFromStandardInput("Input id of event");

        List<Birthday> events;

        while ((events = logic.listOf(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, Integer.class, AttributeFilterType.And)),
                Birthday.class))
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
