package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer requiredSize = null;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeOf(int size) {
        this.requiredSize = size;
        return this;
    }

    @Override
    protected boolean validate(Map<?, ?> value) {
        if (isRequired() && !(value instanceof Map)) {
            return false;
        }
        if (requiredSize != null && value.size() != requiredSize) {
            return false;
        }
        return true;
    }
}
