package main;

import core.Application;
import core.ApplicationContext;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.query.Delete;
import data.query.Insert;
import data.query.Select;
import data.query.Update;
import database.DataBase;
import database.PostgreSQLDataBase;
import database.sql.dialects.PostgreSQLDialect;
import database.sql.dialects.SQLDialect;
import logic.events.Birthday;
import logic.expressions.comparators.OperatorType;

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

        SQLDialect dialect = context.getObject(SQLDialect.class);

        Birthday birthday = new Birthday(LocalDate.of(2020, 10, 11), "Desc", "Person", "gift");

        Insert<Birthday> insert = new Insert<>(Birthday.class);

        insert.setBody(Collections.singletonList(birthday));

        System.out.println(dialect.createQuery(insert));
        Delete<Birthday> delete = new Delete<>(Birthday.class);
        delete.setFilters(Collections.singletonList(new Filter<>("id", OperatorType.Equal, 10, Integer.class, AttributeFilterType.Or)));
        System.out.println(dialect.createQuery(delete));
        Update<Birthday> update = new Update<>(Birthday.class);
        update.setAttributes(Collections.singletonList(new Attribute("gift", "newGift")));
        update.setFilters(Collections.singletonList(new Filter<>("id", OperatorType.Equal, 10, Integer.class, AttributeFilterType.Or)));
        System.out.println(dialect.createQuery(update));
        Select<Birthday> select = new Select<>(Birthday.class);
        select.setFilters(Collections.singletonList(new Filter<>("id", OperatorType.Equal, 10, Integer.class, AttributeFilterType.Or)));
        System.out.println(dialect.createQuery(select));

    }
}
