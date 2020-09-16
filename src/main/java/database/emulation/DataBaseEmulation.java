package database.emulation;

import logic.events.BaseEvent;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataBaseEmulation {

    private HashMap<Integer, BaseEvent> eventHashMap;

    public DataBaseEmulation(){
        eventHashMap = new HashMap<>();
    }

    public HashMap<Integer, BaseEvent> getEventHashMap() {
        return eventHashMap;
    }

    public List<BaseEvent> eventsForKeys(List<Integer> keys){
        List<BaseEvent> events = new ArrayList<>();
        for (Integer key : keys)
            events.add(eventHashMap.get(key));
        return events;
    }

    public void insertEvent(BaseEvent e){
        eventHashMap.put(e.getEventId(), e);
    }

    public void deleteEvent(Integer eventId){
        eventHashMap.remove(eventId);
    }

    public void deleteEvents(List<Integer> keys){
        for (Integer key: keys)
            deleteEvent(key);
    }

    public List<BaseEvent> eventsForPredicate(Predicate<BaseEvent> predicate){
        return eventHashMap.values()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Integer> keysForPredicate(Predicate<BaseEvent> predicate){
       return eventHashMap.values()
               .stream()
               .filter(predicate)
               .map(BaseEvent::getEventId).collect(Collectors.toList());
    }
}
