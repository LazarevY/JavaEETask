package main;

import core.dao.DAO;
import core.dao.SQLBasedDAO;
import core.database.DataBase;
import core.database.PostgreSQLDataBase;
import core.database.sql.dialects.PostgreSQLDialect;
import core.database.sql.dialects.SQLDialect;
import core.inverseofcontrol.boot.Application;
import core.inverseofcontrol.boot.ApplicationContext;
import ui.console.ConsoleUI;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = Application.run("",
                new HashMap<>(Map.of(
                        DataBase.class, PostgreSQLDataBase.class,
                        SQLDialect.class, PostgreSQLDialect.class,
                        DAO.class, SQLBasedDAO.class)));

        ConsoleUI ui = context.getObject(ConsoleUI.class);

        ui.show();


    }
}
