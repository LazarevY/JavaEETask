package data.query;

public abstract class Query<DataType> {

    protected Class<DataType> dataTypeClass;

    public Query(Class<DataType> dataTypeClass) {
        this.dataTypeClass = dataTypeClass;
    }

    public Class<?> getDataTypeClass() {
        return dataTypeClass;
    }

    public abstract QueryType getType();
}
