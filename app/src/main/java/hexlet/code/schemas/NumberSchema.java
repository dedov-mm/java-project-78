package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    protected boolean validate(Integer value) {
        if (positive && value <= 0) {
            return false;
        }
        if (minRange != null && maxRange != null) {
            if (value < minRange || value > maxRange) {
                return false;
            }
        }
        return true;
    }
}
