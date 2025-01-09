package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;
    private String substring = null;

    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        addCheck("minLength", value -> value != null && value.length() >= length); // Проверка на null
        return this;
    }

    public StringSchema contains(String extSubstring) {
        this.substring = extSubstring;
        addCheck("contains", value -> value != null && value.contains(substring)); // Проверка на null
        return this;
    }

    public int getMinLength() {
        return minLength;
    }

    public String getContainsSubstring() {
        return substring;
    }
}
