package database.emulation;

import logic.events.Event;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataBaseEmulation {

    private HashMap<Integer, Event> eventHashMap;

    public DataBaseEmulation(){
        eventHashMap = new HashMap<>();
    }

    public HashMap<Integer, Event> getEventHashMap() {
        return eventHashMap;
    }

    public List<Event> eventsForKeys(List<Integer> keys){
        List<Event> events = new ArrayList<>();
        for (Integer key : keys)
            events.add(eventHashMap.get(key));
        return events;
    }

    public void insertEvent(Event e){
        e.setId(idCounter++);
        eventHashMap.put(e.getId(), e);
    }

    public void deleteEvent(Integer eventId){
        eventHashMap.remove(eventId);
    }

    public void deleteEvents(List<Integer> keys){
        for (Integer key: keys)
            deleteEvent(key);
    }

    public List<Event> eventsForPredicate(Predicate<Event> predicate){
        return eventHashMap.values()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Integer> keysForPredicate(Predicate<Event> predicate){
       return eventHashMap.values()
               .stream()
               .filter(predicate)
               .map(Event::getId).collect(Collectors.toList());
    }

    private static int idCounter;
}
