package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    void testValidator() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertNotNull(schema);
        assertTrue(schema.isValid(""));
    }
}
