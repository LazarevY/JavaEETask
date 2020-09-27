package data.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Filter;

public class AttributeMapper {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static JsonNode createJsonNode(Filter filter){
        return mapper.convertValue(filter, JsonNode.class);
    }
}
