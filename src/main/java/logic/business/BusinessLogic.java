package logic.business;

import core.dao.DAO;
import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.Singleton;
import data.Attribute;
import data.Filter;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class BusinessLogic {

    @InjectByType
    private DAO dao;

    private List<Class<? extends Event>> eventsImplClasses;

    @PostConstruct
    private void init() {
        eventsImplClasses = Arrays.asList(Appointment.class, Birthday.class);
    }


    public List<Event> getAllEvents(List<Filter<?>> filters) {
        return getAllEvents(filters, Comparator.comparingInt(e -> e.getEventDate().getYear()));
    }

    public List<Event> getAllEvents(List<Filter<?>> filters, Comparator<Event> toSortComparator) {
        List<Event> events = new ArrayList<>();

        for (Class<? extends Event> implClass : eventsImplClasses) {
            events.addAll(listOf(filters, implClass));
        }

        events.sort(toSortComparator);

        return events;

    }

    public <T extends Event> List<T> listOf(List<Filter<?>> filters, Class<T> tClass) {
        return listOf(filters, tClass, Comparator.comparingInt(e -> e.getEventDate().getYear()));
    }

    public <T extends Event> List<T> listOf(List<Filter<?>> filters, Class<T> tClass, Comparator<T> toSortComparator) {
        Select<T> select = new Select<>(tClass);
        select.setFilters(filters);
        List<T> values = dao.select(select);
        return values.stream().sorted(toSortComparator).collect(Collectors.toList());
    }

    public void addEvents(List<Event> events) {
        for (Event e : events) {
            Insert insert = new Insert<>(e.getClass());
            insert.setBody(Collections.singletonList(e));
            dao.insert(insert);
        }
    }

    public <T extends Event> void updateEvents(List<Attribute> newAttributes, List<Filter<?>> filters, Class<T> tClass) {
        Update<T> update = new Update<>(tClass);
        update.setAttributes(newAttributes);
        update.setFilters(filters);
        dao.update(update);
    }

    public void updateAllEvents(List<Attribute> newAttributes, List<Filter<?>> filters) {
        for (Class<? extends Event> implClass : eventsImplClasses) {
            updateEvents(newAttributes, filters, implClass);
        }
    }

    public void removeEvents(List<Filter<?>> filters) {
        for (Class<? extends Event> implClass : eventsImplClasses) {
            removeEventsType(filters, implClass);
        }
    }

    public <T extends Event> void removeEventsType(List<Filter<?>> filters, Class<T> type) {
        Delete<? extends Event> delete = new Delete<>(type);
        delete.setFilters(filters);
        dao.delete(delete);
    }
}
