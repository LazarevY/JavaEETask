package main;

import data.dao.HashMapDao;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;
import ui.console.commands.BaseActionChoose;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

public class Main {


    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BusinessLogic logic = new BusinessLogic();
        HashMapDao<Birthday> birthdayHashMapDao = new HashMapDao<>();
        HashMapDao<Appointment> appointmentHashMapDao = new HashMapDao<>();

        logic.registerDao(Birthday.class, birthdayHashMapDao);
        logic.registerDao(Appointment.class, appointmentHashMapDao);

        Appointment a1 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 30),
                        "Desc",
                        "Person",
                        LocalTime.of(18,10));
        Appointment a2 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 31),
                        "Desc",
                        "Person",
                        LocalTime.of(12, 30));
        Appointment a3 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 30),
                        "Desc",
                        "Person",
                        LocalTime.of(14,15));
        Appointment a4 =
                new Appointment(LocalDate.of(2019, Month.DECEMBER, 27),
                        "Desc",
                        "Person",
                        LocalTime.of(17, 0));

        logic.addEvents(Arrays.asList(a1, a2, a3, a4));
        BaseActionChoose choose = new BaseActionChoose(logic);

        choose.execute(Collections.emptyMap());
    }
}
