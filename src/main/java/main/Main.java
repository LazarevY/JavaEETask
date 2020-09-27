package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.AttributeFilterType;
import data.Filter;
import data.json.AttributeMapper;

import java.io.File;
import java.io.IOException;

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


    }
}
