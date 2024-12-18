package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer requiredSize = null;
    private Map<String, BaseSchema<?>> shapeSchemas = null;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.requiredSize = size;
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        return this;
    }

    @Override
    protected boolean isValidType(Object value) {
        return value instanceof Map;
    }

    @Override
    protected boolean validate(Map<?, ?> value) {
        if (requiredSize != null && value.size() != requiredSize) {
            return false;
        }
        if (shapeSchemas != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!value.containsKey(key) || !schema.isValid(value.get(key))) {
                    return false;
                }
            }
        }

        return true;
    }
}
