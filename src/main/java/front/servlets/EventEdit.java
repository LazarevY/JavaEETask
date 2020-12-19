package front.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data.Attribute;
import data.AttributeFilterType;
import data.Filter;
import data.json.serialize.LocalDateSerialize;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.expressions.comparators.OperatorType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Lazarev Yaroslav
 */

@WebServlet(value = "/main/edit")
public class EventEdit extends HttpServlet {
    private BusinessLogic logic;
    private ObjectMapper json = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String body = req.getReader().lines().collect(Collectors.joining());

        JsonNode n = json.readTree(body);

        String type = n.get("type").asText();

        if (type.equals("birthday")) {
            updateBirthday(n);
        } else if (type.equals("appointment")) {
            updateAppointment(n);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String body = req.getReader().lines().collect(Collectors.joining());

        JsonNode n = json.readTree(body);

        String type = n.get("type").asText();

        if (type.equals("birthday")) {
            deleteBirthday(n.get("id").asInt());
        } else if (type.equals("appointment")) {
            deleteAppointment(n.get("id").asInt());
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

    private void updateBirthday(JsonNode node) {
        LocalDate date = LocalDate.parse(node.get("date").asText().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logic.updateEvents(
                Arrays.asList(
                        new Attribute("description", node.get("desc").asText()),
                        new Attribute("date", date),
                        new Attribute("targetperson", node.get("person").asText()),
                        new Attribute("gift", node.get("gift").asText())
                ),
                Collections.singletonList(new Filter<>("id", OperatorType.Equal, node.get("id").asInt(),
                        AttributeFilterType.And)),
                Birthday.class);
    }

    private void updateAppointment(JsonNode node) {
        LocalDate date = LocalDate.parse(node.get("date").asText().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        JsonNode time = node.get("time");
        LocalTime appTime = LocalTime.of(time.get("HH").asInt(), time.get("mm").asInt(), 0);
        logic.updateEvents(
                Arrays.asList(
                        new Attribute("description", node.get("desc").asText()),
                        new Attribute("date", date),
                        new Attribute("targetperson", node.get("person").asText()),
                        new Attribute("appointmenttime", appTime)
                ),
                Collections.singletonList(new Filter<>("id", OperatorType.Equal, node.get("id").asInt(),
                        AttributeFilterType.And)),
                Appointment.class);
    }

    private void deleteBirthday(int id){
        logic.removeEventsType(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, AttributeFilterType.And)),
                Birthday.class);
    }

    private void deleteAppointment(int id){
        logic.removeEventsType(Collections.singletonList(
                new Filter<>("id", OperatorType.Equal, id, AttributeFilterType.And)),
                Appointment.class);
    }
}
