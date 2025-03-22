package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class to read expected values from an external JSON file.
 */
public class JsonUtils {
    private static final String EXPECTED_VALUES_FILE = "resources/expectedValues.json";

    /**
     * Reads expected values from expectedValues.json.
     *
     * @return Map of expected attribute names and their expected values.
     */
    public static Map<String, Object> getExpectedValues() {
        Map<String, Object> expectedValues = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(EXPECTED_VALUES_FILE));

            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                expectedValues.put(entry.getKey(), objectMapper.treeToValue(entry.getValue(), Object.class));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading expected values file", e);
        }
        return expectedValues;
    }
}
