package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    void testRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(Map.of()));
    }

    @Test
    void testSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required().sizeof(2);

        assertTrue(schema.isValid(Map.of("key1", 1, "key2", 2)));
        assertFalse(schema.isValid(Map.of("key1", 1)));
        assertFalse(schema.isValid(Map.of()));
    }

    @Test
    void testShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("key1", v.string().required());
        schemas.put("key2", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> validMap = Map.of(
                "key1", "value",
                "key2", 10
        );
        assertTrue(schema.isValid(validMap));

        Map<String, Object> invalidMap = Map.of(
                "key1", "",
                "key2", -5
        );
        assertFalse(schema.isValid(invalidMap));
    }
}
