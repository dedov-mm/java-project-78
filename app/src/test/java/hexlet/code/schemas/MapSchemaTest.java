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
        schema.required().sizeOf(2);

        assertTrue(schema.isValid(Map.of("key1", 1, "key2", 2)));
        assertFalse(schema.isValid(Map.of("key1", 1)));
        assertFalse(schema.isValid(Map.of()));
    }
}
