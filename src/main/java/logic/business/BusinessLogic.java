package logic.business;

import data.Attribute;
import data.dao.DAO;
import data.Filter;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import logic.events.Event;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessLogic {

    private HashMap<Class<? extends Event>, DAO> daoHashMap;

    public BusinessLogic() {
        this.daoHashMap = new HashMap<>();
    }

    public <DataType extends Event> void registerDao(Class<DataType> dataTypeClass, DAO<DataType> dao){
        daoHashMap.put(dataTypeClass, dao);
    }

    private <DataType> DAO<DataType> getDao(Class<DataType> dataTypeClass){
        return (DAO<DataType>) daoHashMap.get(dataTypeClass);
    }

    public List<Event> getAllEvents(List<Filter<?>> filters){
        return getAllEvents(filters, Comparator.comparingInt(e -> e.getEventDate().getYear()));
    }
    public List<Event> getAllEvents(List<Filter<?>> filters, Comparator<Event> toSortComparator){
        List<Event> events = new ArrayList<>();

        for (Map.Entry<Class<? extends Event>, DAO> entry: daoHashMap.entrySet()){
            Select<? extends Event> select = new Select<>(entry.getKey());
            select.setFilters(filters);
            events.addAll(entry.getValue().select(select));
        }

        events.sort(toSortComparator);

        return events;

    }

    public <T extends Event> List<T> listOf(List<Filter<?>> filters, Class<T> tClass){
        return listOf(filters, tClass, Comparator.comparingInt(e -> e.getEventDate().getYear()));
    }

    public <T extends Event> List<T> listOf(List<Filter<?>> filters, Class<T> tClass, Comparator<T> toSortComparator){
        Select<T> select = new Select<>(tClass);
        select.setFilters(filters);
        return getDao(tClass).select(select).stream().sorted(toSortComparator).collect(Collectors.toList());
    }

    public void addEvents(List<Event> events){
        for (Event e: events){
            Insert insert = new Insert<>(e.getClass());
            insert.setBody(Collections.singletonList(e));
            getDao(e.getClass()).insert(insert);
        }
    }

    public <T extends Event> void updateEvents(List<Attribute> newAttributes, List<Filter<?>> filters, Class<T> tClass){
        Update<T> update = new Update<>(tClass);
        update.setAttributes(newAttributes);
        update.setFilters(filters);
    }

    public void updateAllEvents(List<Attribute> newAttributes, List<Filter<?>> filters){
        for (Map.Entry<Class<? extends Event>, DAO> entry: daoHashMap.entrySet()){
            Update<? extends Event> update = new Update<>(entry.getKey());
            update.setFilters(filters);
            update.setAttributes(newAttributes);
            entry.getValue().update(update);
        }
    }

    public void removeEvents(List<Filter<?>> filters){
        for (Map.Entry<Class<? extends Event>, DAO> entry: daoHashMap.entrySet()){
            Delete<? extends Event> delete = new Delete<>(entry.getKey());
            delete.setFilters(filters);
            entry.getValue().delete(delete);
        }
    }

}
