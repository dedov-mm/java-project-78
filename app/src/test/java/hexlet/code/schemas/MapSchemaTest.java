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

        assertTrue(schema.isValid(Map.of()));
        assertFalse(schema.isValid(null));
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
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));

        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Anna");
        human4.put("lastName", "Bell");
        assertTrue(schema.isValid(human4));
    }
}
