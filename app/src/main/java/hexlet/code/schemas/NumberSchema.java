package hexlet.code.schemas;

public class NumberSchema {
    private boolean required;
    private boolean positive;
    private Integer minRange = null;
    private Integer maxRange = null;

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        return this;
    }

    public boolean isValid(Integer number) {
        if (required && number == null) {
            return false;
        }
        if (number == null) {
            return true;
        }
        if (positive && number <= 0) {
            return false;
        }
        if (minRange != null && maxRange != null) {
            if (number < minRange || number > maxRange) {
                return false;
            }
        }
        return true;
    }
}
