package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    void testValidatorString() {
        Validator v = new Validator();
        StringSchema stringSchema = v.string();

        assertNotNull(stringSchema);
        assertTrue(stringSchema.isValid(""));
    }

    @Test
    void testValidatorNumber() {
        Validator v = new Validator();
        NumberSchema numberSchema = v.number();

        assertNotNull(numberSchema);
        assertTrue(numberSchema.isValid(0));
    }
}
