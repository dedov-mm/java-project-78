package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean positiveOnly = false;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        this.positiveOnly = true;
        addCheck("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int extMin, int extMax) {
        this.min = extMin;
        this.max = extMax;
        addCheck("range", value -> value == null || (value >= min && value <= max));
        return this;
    }

    public boolean isPositiveOnly() {
        return positiveOnly;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

