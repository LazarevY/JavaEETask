package data.query;

import data.Filter;

import java.util.List;

public class Delete<DataType> extends Query<DataType> {

    private List<Filter> filters;

    public Delete(Class<DataType> dataTypeClass) {
        super(dataTypeClass);
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public QueryType getType() {
        return QueryType.Delete;
    }
}
