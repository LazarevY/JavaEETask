package data.query;

import java.util.ArrayList;
import java.util.List;

public class Insert<DataType> extends Query<DataType> {

    private List<DataType> body = new ArrayList<>();

    public Insert(Class<DataType> dataTypeClass) {
        super(dataTypeClass);
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
