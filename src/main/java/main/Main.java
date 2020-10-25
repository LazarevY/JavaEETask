package main;

import core.Application;
import core.ApplicationContext;
import database.DataBase;
import database.PostgreSQLDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException{

        ApplicationContext context = Application.run("",
                new HashMap<>(Map.of(DataBase.class, PostgreSQLDataBase.class)));

        PostgreSQLDataBase dataBase = context.getObject(PostgreSQLDataBase.class);

        ResultSet resultSet1 = dataBase.executeQuery("SELECT * FROM car_sharing_schema.autos");
        while (resultSet1.next()){
            System.out.println(resultSet1.getString(4));
        }

        dataBase.disconnect();
    }
}
