package main;


import core.dao.DAO;
import core.dao.SQLBasedDAO;
import core.inverseofcontrol.boot.Application;
import core.inverseofcontrol.boot.ApplicationContext;
import ui.UI;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = Application.run("",
                new HashMap<>(Map.of(DAO.class, SQLBasedDAO.class)));

        UI ui = context.getObject(UI.class);
        ui.show();

    }
}
