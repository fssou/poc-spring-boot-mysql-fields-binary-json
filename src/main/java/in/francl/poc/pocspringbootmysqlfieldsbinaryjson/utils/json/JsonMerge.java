package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

public class JsonMerge {

    private final ObjectMapper objectMapper;

    private JsonMerge(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static JsonMerge of(ObjectMapper objectMapper) {
        return new JsonMerge(objectMapper);
    }

    public static JsonMerge of() {
        return new JsonMerge(new ObjectMapper());
    }

    public Map<String, Object> merge(Map<String, Object> mergeable, Map<String, Object> merge) {
        var mergeableJson = objectMapper.convertValue(mergeable, ObjectNode.class);
        var mergeJson = objectMapper.convertValue(merge, ObjectNode.class);
        mergeJson(mergeableJson, mergeJson);
        var mapTypeStringObject = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        Map<String, Object> mergedMap = objectMapper.convertValue(mergeableJson, mapTypeStringObject);
        return mergedMap;
    }

    private void mergeJson(ObjectNode mainNode, ObjectNode updateNode) {
        updateNode.fieldNames().forEachRemaining((fieldName) -> {
            JsonNode jsonNodeMain = mainNode.get(fieldName);
            JsonNode jsonNodeUpdate = updateNode.get(fieldName);
            if (jsonNodeMain != null && jsonNodeMain.isObject() && jsonNodeUpdate.isObject()) {
                mergeJson((ObjectNode)jsonNodeMain, (ObjectNode) jsonNodeUpdate);
            } else {
                if (mainNode instanceof ObjectNode) {
                    mainNode.replace(fieldName, jsonNodeUpdate);
                }
            }
        });
    }

}
