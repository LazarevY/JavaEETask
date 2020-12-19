package front.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data.json.serialize.LocalDateSerialize;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Lazarev Yaroslav
 */

@WebServlet(value = "/main/add")
public class EventAdd extends HttpServlet {
    private BusinessLogic logic;
    private ObjectMapper json = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String body = req.getReader().lines().collect(Collectors.joining());

        JsonNode n = json.readTree(body);

        String type = n.get("type").asText();

        if (type.equals("birthday")) {
            addBirthday(n);
        } else if (type.equals("appointment")) {
            addAppointment(n);
        }

    }
    @Override
    public void init() throws ServletException {
        super.init();
        SimpleModule m = new SimpleModule();
        m.addSerializer(LocalDate.class, new LocalDateSerialize());
        json.registerModule(m);
        logic = Boot.getContext().getObject(BusinessLogic.class);
    }

    private void addAppointment(JsonNode node) {
        LocalDate date = LocalDate.parse(node.get("date").asText().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        JsonNode time = node.get("time");
        LocalTime appTime = LocalTime.of(time.get("HH").asInt(), time.get("mm").asInt(), 0);
        Appointment a = new Appointment(date, node.get("desc").asText(), node.get("person").asText(), appTime);
        logic.addEvents(Collections.singletonList(a));
    }

    private void addBirthday(JsonNode node) {
        LocalDate date = LocalDate.parse(node.get("date").asText().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Birthday b = new Birthday(date, node.get("desc").asText(), node.get("person").asText(), node.get("gift").asText());
        logic.addEvents(Collections.singletonList(b));
    }

}
