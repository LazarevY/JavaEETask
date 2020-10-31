package data.query;

import data.Filter;

import java.util.ArrayList;
import java.util.List;

public class Select<DataType> extends Query<DataType> {

    private List<Filter<?>> filters = new ArrayList<>();

    public Select(Class<DataType> dataTypeClass) {
        super(dataTypeClass);
    }

    public List<Filter<?>> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter<?>> filters) {
        this.filters = filters;
    }

    @Override
    public QueryType getType() {
        return null;
    }
}
