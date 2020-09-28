package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.AttributeFilterType;
import data.Filter;
import data.json.AttributeMapper;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.ConditionChecker;
import logic.expressions.utils.ExpressionsUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class Main {


    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String json = "{\n" +
                "  \"attribute\": \"day\",\n" +
                "  \"operator\": \"=\",\n" +
                "  \"value\": 20,\n" +
                "  \"type\": \"And\"\n" +
                "}";

        Filter f = mapper.readValue(json, Filter.class);
        f = mapper.treeToValue(AttributeMapper.createJsonNode(f), Filter.class);

        System.out.println(f.getAttribute());
        System.out.println(f.getOperator());
        System.out.println(f.getValue().toString());
        System.out.println(f.getType());

        Condition<Event, Integer> condition = (Condition<Event, Integer>) ExpressionsUtils.createConditionForFilter(f);
        Birthday b =
                new Birthday(
                        LocalDate.of(2020, Month.APRIL, 20),
                        "Desc",
                        "Name",
                        "Deeeep"
                );
        System.out.println(condition.check(b));

        System.out.println(b instanceof Event);


    }
}
