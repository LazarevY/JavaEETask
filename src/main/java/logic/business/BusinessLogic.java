package logic.business;

import core.annotations.InjectByType;
import data.Attribute;
import data.Filter;
import data.dao.DAO;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Event;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessLogic {

    @InjectByType
    private DAO dao;


    public List<Event> getAllEvents(List<Filter<?>> filters) {
        return getAllEvents(filters, Comparator.comparingInt(e -> e.getEventDate().getYear()));
    }

    public List<Event> getAllEvents(List<Filter<?>> filters, Comparator<Event> toSortComparator) {
        List<Event> events = new ArrayList<>();

        Select<? extends Event> select = new Select<>(Event.class);
        select.setFilters(filters);
        events.addAll((List<? extends Event>) dao.select(select));

        events.sort(toSortComparator);

        return events;

    }

    public <T extends Event> List<T> listOf(List<Filter<?>> filters, Class<T> tClass) {
        return listOf(filters, tClass, Comparator.comparingInt(e -> e.getEventDate().getYear()));
    }

    public <T extends Event> List<T> listOf(List<Filter<?>> filters, Class<T> tClass, Comparator<T> toSortComparator) {
        Select<T> select = new Select<>(tClass);
        select.setFilters(filters);
        List<T> values = (List<T>) dao.select(select);
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
    }

    public void updateAllEvents(List<Attribute> newAttributes, List<Filter<?>> filters) {
        Update<? extends Event> update = new Update<>(Event.class);
        update.setFilters(filters);
        update.setAttributes(newAttributes);
        dao.update(update);
    }

    public void removeEvents(List<Filter<?>> filters) {
        Delete<? extends Event> delete = new Delete<>(Event.class);
        delete.setFilters(filters);
        dao.delete(delete);
    }
}
