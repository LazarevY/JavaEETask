package main;

import core.Application;
import core.ApplicationContext;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.dao.DAO;
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
import ui.console.ConsoleUI;

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
                        SQLDialect.class, PostgreSQLDialect.class,
                        DAO.class, SQLBasedDAO.class)));

        ConsoleUI ui = context.getObject(ConsoleUI.class);

        ui.show();



    }
}
