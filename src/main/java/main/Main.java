package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(new File("resources/test.json"));

        JsonNode node1 = node.get("color");

        String color = node1.get("f").asText();

        System.out.println(color);

    }
}
