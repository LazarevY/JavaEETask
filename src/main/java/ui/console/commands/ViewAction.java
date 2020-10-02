package ui.console.commands;

import logic.business.BusinessLogic;
import logic.events.Event;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ViewAction implements Command {

    private BusinessLogic businessLogic;

    public ViewAction(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public int execute(Map<String, Object> args) {
        List<Event> events = businessLogic.getAllEvents(Collections.emptyList());
        for (Event e: events)
            System.out.println(e.shortDescription());
        return 0;
    }
}
