package data.query;

import java.util.List;

public class Insert<DataType> extends Query<DataType> {

    private List<DataType> body;

    public Insert(Class<DataType> dataTypeClass) {
        super(dataTypeClass);
        body = null;
    }

    public List<DataType> getBody() {
        return body;
    }

    public void setBody(List<DataType> body) {
        this.body = body;
    }

    @Override
    public QueryType getType() {
        return QueryType.Insert;
    }
}
