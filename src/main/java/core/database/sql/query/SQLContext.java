package core.database.sql.query;

import core.database.DataBase;
import core.database.sql.dialects.SQLDialect;
import core.inverseofcontrol.annotations.InjectByType;
import data.query.Query;

public class SQLContext {

    @InjectByType
    private DataBase dataBase;

    @InjectByType
    private SQLDialect sqlDialect;

    public QueryResponse execQuery(Query query) {

        String queryStr = sqlDialect.createQuery(query);

        if (queryStr == null) {
            return new QueryResponse(null, -1, "Error by create query");
        }

        return dataBase.executeQuery(queryStr);


    }

}
