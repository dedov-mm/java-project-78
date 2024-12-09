package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class StringSchemaTest {
    @Test
    void testRequired() {
        StringSchema schema = new StringSchema();
        schema.required();

        assertTrue(schema.isValid("test"));
    }

    @Test
    void testMinLength() {
        StringSchema schema = new StringSchema();
        schema.minLength(5);

        var expected = 5;
        var actual = schema.getMinLength();

        assertEquals(expected, actual);
    }

    @Test
    void testContains() {
        StringSchema schema = new StringSchema();
        schema.contains("test");

        var expected = "test";
        var actual = schema.getSubstring();

        assertEquals(expected, actual);
    }

    @Test
    void testIsValid() {
        StringSchema schema = new StringSchema();
        schema.required().minLength(4).contains("test");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("goodtest"));
    }

    @Test
    void testOverrideValidators() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();
        assertFalse(schema.isValid(null), "required должен сделать null недопустимым");
        assertFalse(schema.isValid(""), "required должен сделать пустую строку недопустимой");

        schema.minLength(3);
        assertTrue(schema.isValid("abc"), "Строка длиной 3 должна быть валидной");
        assertFalse(schema.isValid("ab"), "Строка короче 3 должна быть невалидной");

        schema.minLength(5);
        assertTrue(schema.isValid("abcde"), "Строка длиной 5 должна быть валидной");
        assertFalse(schema.isValid("abc"), "Строка короче 5 должна быть невалидной");

        schema.contains("test");
        assertTrue(schema.isValid("this is a test"), "Строка, содержащая test, должна быть валидной");
        assertFalse(schema.isValid("this is a quiz"), "Строка без test должна быть невалидной");

        schema.contains("quiz");
        assertTrue(schema.isValid("this is a quiz"), "Строка, содержащая quiz, должна быть валидной");
        assertFalse(schema.isValid("this is a test"), "Строка без quiz должна быть невалидной");
    }
}
