package logic.business;

import data.DAO;
import logic.events.Event;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BusinessLogic {

    private DAO dao;

    public List<String> getEventsDescription(Predicate<Event> predicate){
        return dao.select(predicate).stream().map(Event::shortDescription).collect(Collectors.toList());
    }

    public Event get(Predicate<Event> predicate){
        List<Event> lst = dao.select(predicate);
        if (lst.size() > 1)
            throw new IllegalArgumentException("Predicate must set unique instance of event");
        if (lst.size() == 0)
            throw new IllegalArgumentException("Predicate doesn't set any event");
        return lst.get(0);
    }

    public void addEvent(Event event){
        dao.insert(event);
    }

    public void updateEvents(Event newEvent, Predicate<Event> predicate){
        dao.update(newEvent, predicate);
    }

    public void removeEvents(Predicate<Event> predicate){
        dao.delete(predicate);
    }

}
