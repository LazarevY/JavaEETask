package ui.console.commands;

import console.io.InputManager;
import core.annotations.InjectByType;
import logic.events.Event;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SelectSortParameter implements Command {

    private Map<String, Comparator<Event>> comparatorMap;

    @InjectByType
    private InputManager inputManager;

    @PostConstruct
    public void init() {
        comparatorMap = new HashMap<String, Comparator<Event>>() {{
            put("d", Comparator.comparingInt(event -> event.getEventDate().getDayOfMonth()));
            put("m", Comparator.comparingInt(event -> event.getEventDate().getMonthValue()));
            put("y", Comparator.comparingInt(event -> event.getEventDate().getYear()));
        }};
    }


    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Command.printTemplate("Select Sort Parameter",
                "d: Day\n" +
                        "m: Month\n" +
                        "y: Year\n");
        String parameter = inputManager
                .getStringFromStandardInput("Input sort parameter");

        while (!comparatorMap.containsKey(parameter)) {
            parameter = inputManager
                    .getStringFromStandardInput("Wrong parameter. Input again");
        }
        ExecuteResult res = new ExecuteResult();
        res.addReturnParameter("sort", comparatorMap.get(parameter));
        return res;
    }
}
