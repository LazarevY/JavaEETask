package main;

import core.Application;
import core.ApplicationContext;
import data.query.Insert;
import database.sql.dialects.SQLDialect;
import logic.events.Birthday;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = Application.run("", new HashMap<>());

        SQLDialect sqlDialect = context.getObject(SQLDialect.class);

        Insert<Birthday> insert = new Insert<>(Birthday.class);

        Birthday birthday = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");
        insert.setBody(Collections.singletonList(birthday));

        System.out.println(sqlDialect.createQuery(insert));

    }
}
