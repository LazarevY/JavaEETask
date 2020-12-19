package front.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data.AttributeFilterType;
import data.Filter;
import data.json.serialize.LocalDateSerialize;
import logic.business.BusinessLogic;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(value = "/main")
public class MainServlet extends HttpServlet {

    private BusinessLogic logic;
    private ObjectMapper json = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        super.init();
        SimpleModule m = new SimpleModule();
        m.addSerializer(LocalDate.class, new LocalDateSerialize());
        json.registerModule(m);
        logic = Boot.getContext().getObject(BusinessLogic.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String events = json.writeValueAsString(logic.getAllEvents(Collections.emptyList()));
        out.print(String.format("{\"events\": %s}", events));
        out.flush();
    }

    @SafeVarargs
    private List<? extends Event> getEvents(JsonNode n, Class<? extends Event>... classes) {
        Set<Class<? extends Event>> classes1 = Set.of(classes);
        String sort = n.get("sort").asText();
        boolean desc = n.get("sortDesc").asBoolean();
        if (classes1.contains(Event.class)) {
            return logic.getAllEvents(getFilters(n), getComparator(sort, desc));
        }
        Class<? extends Event> c = classes1.iterator().next();
        return logic.listOf(getFilters(n), c, getComparator(sort, desc));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String body = req.getReader().lines().collect(Collectors.joining());

        JsonNode n = json.readTree(body);

        JsonNode types = n.get("types");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (types.size() == 0 || types.size() == 2) {
            List<Event> events = (List<Event>) getEvents(n, Event.class);
            String eventsStr = json.writeValueAsString(events);
            out.print(String.format("{\"events\": %s}", eventsStr));
            out.flush();
        } else if (types.get(0).asText().equals("birthday")) {
            List<Event> events = (List<Event>) getEvents(n, Birthday.class);
            String eventsStr = json.writeValueAsString(events);
            out.print(String.format("{\"events\": %s}", eventsStr));
            out.flush();
        } else {
            List<Event> events = (List<Event>) getEvents(n, Appointment.class);
            String eventsStr = json.writeValueAsString(events);
            out.print(String.format("{\"events\": %s}", eventsStr));
            out.flush();
        }

    }

    private List<Filter<?>> getFilters(JsonNode node) {
        JsonNode beginDate = node.get("beginDate");
        List<Filter<?>> filters = new ArrayList<>();
        if (!beginDate.isNull()) {
            LocalDate begin = LocalDate.parse(beginDate.asText().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            filters.add(new Filter<>("date", OperatorType.MoreEqual, begin, AttributeFilterType.And));
        }

        JsonNode endDate = node.get("endDate");
        if (!endDate.isNull()) {
            LocalDate end = LocalDate.parse(endDate.asText().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            filters.add(new Filter<>("date", OperatorType.LessEqual, end, AttributeFilterType.And));
        }
        return filters;
    }

    private <T extends Event> Comparator<T> getComparator(String param, boolean desc) {
        switch (param) {
            case "month":
                Comparator<T> c = Comparator.comparingInt(event -> event.getEventDate().getMonthValue());
                return desc ? c.reversed() : c;
            case "day":
                Comparator<T> c1 = Comparator.comparingInt(event -> event.getEventDate().getDayOfMonth());
                return desc ? c1.reversed() : c1;
            default:
                return Comparator.comparingInt(event -> event.getEventDate().getYear());
        }
    }
}