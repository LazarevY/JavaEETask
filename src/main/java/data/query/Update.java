package data.query;

import data.Attribute;
import data.Filter;

import java.util.ArrayList;
import java.util.List;

public class Update<DataType> extends Query<DataType> {

    private List<Attribute> attributes = new ArrayList<>();
    private List<Filter<?>> filters = new ArrayList<>();

    public Update(Class<DataType> dataTypeClass) {
        super(dataTypeClass);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Filter<?>> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter<?>> filters) {
        this.filters = filters;
    }

    @Override
    public QueryType getType() {
        return QueryType.Update;
    }
}
