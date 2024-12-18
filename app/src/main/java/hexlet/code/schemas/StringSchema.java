package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;
    private String containsSubstring = null;

    public int getMinLength() {
        return minLength;
    }

    public String getContainsSubstring() {
        return containsSubstring;
    }

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    @Override
    protected boolean validate(String value) {
        if (isRequired() && value.isEmpty()) {
            return false;
        }
        if (value.length() < minLength) {
            return false;
        }
        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }
        return true;
    }


}
