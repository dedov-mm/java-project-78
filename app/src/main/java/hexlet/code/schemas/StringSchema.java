package hexlet.code.schemas;


public class StringSchema {
    private boolean required;
    private int minLength;
    private String substring;

    public boolean isRequired() {
        return required;
    }

    public int getMinLength() {
        return minLength;
    }

    public String getSubstring() {
        return substring;
    }

    public StringSchema() {
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String value) {
        this.substring = value;
        return this;
    }

    public boolean isValid(String string) {
        if (required && (string == null || string.isEmpty())) {
            return false;
        }
        if (string.length() < minLength) {
            return false;
        }
        if (substring != null && !string.contains(substring)) {
            return false;
        }
        return true;
    }
}
