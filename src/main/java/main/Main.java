package main;

import core.Application;
import core.ApplicationContext;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.dao.SQLBasedDAO;
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
import java.util.List;
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


        Select<Birthday> select = new Select<>(Birthday.class);

        SQLBasedDAO dao = context.getObject(SQLBasedDAO.class);

        List<Birthday> select1 = dao.select(select);

        for (Birthday birthday1 : select1) {
            System.out.println(birthday1.shortDescription());
        }


    }
}
