package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private int requiredSize = -1;

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeOf(int size) {
        this.requiredSize = size;
        addCheck("sizeOf", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        addCheck("shape", map -> {
            if (map == null) {
                return false;
            }

            for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object value = map.get(key);

                if (value == null || !map.containsKey(key) || !isValidWithSchema(schema, value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @SuppressWarnings("unchecked")
    private <T> boolean isValidWithSchema(BaseSchema<T> schema, Object value) {
        if (value == null) {
            return true;
        }
        try {
            return schema.isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int getRequiredSize() {
        return requiredSize;
    }
}
