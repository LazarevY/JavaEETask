package main;

import core.Application;
import core.ApplicationContext;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.query.*;
import database.DataBase;
import database.PostgreSQLDataBase;
import database.QueryResponse;
import database.SQLContext;
import database.sql.dialects.PostgreSQLDialect;
import database.sql.dialects.SQLDialect;
import logic.events.Birthday;
import logic.expressions.comparators.OperatorType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException{

        ApplicationContext context = Application.run("",
                new HashMap<>(Map.of(
                        DataBase.class, PostgreSQLDataBase.class,
                        SQLDialect.class, PostgreSQLDialect.class)));


        SQLContext sqlContext = context.getObject(SQLContext.class);

        Birthday birthday = new Birthday(LocalDate.of(2020, 4, 15),
                "Desc1",
                "Pers",
                "Giffft");

        Delete<Birthday> delete = new Delete<>(Birthday.class);

        delete.setFilters(Collections.singletonList(new Filter<>("id", OperatorType.Equal, 1, AttributeFilterType.And)));

        QueryResponse r1 = sqlContext.execQuery(delete);
        System.out.println(r1.getMsg());

        Select<Birthday> select = new Select<>(Birthday.class);

        QueryResponse response  = sqlContext.execQuery(select);

        if (response.getCode() == 0 && response.getResultSet() != null){
            ResultSet set = response.getResultSet();

            while (set.next()){
                StringBuilder builder = new StringBuilder();

                for (int col = 1; col <= set.getMetaData().getColumnCount(); ++col)
                    builder.append(set.getString(col)).append(" ");

                System.out.println(builder.toString());

            }

        }
        System.out.println(response.getMsg());


    }
}
