package data;

import logic.events.Event;

import java.util.List;
import java.util.function.Predicate;

public interface DAO {

    void insert(Event e);
    void delete(Predicate<Event> predicate);
    List<Event> select(Predicate<Event> predicate);
    void update(Event newValue, Predicate<Event> predicate);

}
