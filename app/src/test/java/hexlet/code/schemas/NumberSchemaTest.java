package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    void testRequired() {
        NumberSchema schema = new NumberSchema();

        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testPositive() {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testRange() {
        NumberSchema schema = new NumberSchema();
        schema.required().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testIsValid() {
        NumberSchema schema = new NumberSchema();
        schema.required().positive().range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testOverrideValidators() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null), "пока не вызван required, null считается валидным");
        schema.required();
        assertFalse(schema.isValid(null), "required должен сделать null недопустимым");

        schema.positive();
        assertTrue(schema.isValid(10), "положительное число 10 должно быть валидным");
        assertFalse(schema.isValid(-10), "после вызова positive отрицательное число -10 должно быть невалидным");

        schema.range(5, 10);
        assertTrue(schema.isValid(10), "число 10 в диапазоне от 5 до 10 должно быть валидным");
        assertFalse(schema.isValid(11), "число 11 не в диапазоне от 5 до 10 должно быть невалидным");

        schema.range(15, 20);
        assertTrue(schema.isValid(17), "число 17 в диапазоне от 15 до 20 должно быть валидным");
        assertFalse(schema.isValid(7), "число 7 не в диапазоне от 15 до 20 должно быть невалидным");
    }
}
