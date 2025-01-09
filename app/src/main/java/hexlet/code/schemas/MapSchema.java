package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    public MapSchema required() {
        addCheck("required", value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeOf(int size) {
        addCheck("sizeOf", value -> value != null && value.size() == size);
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

                if (!isValidWithSchema(schema, value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    private <T> boolean isValidWithSchema(BaseSchema<T> schema, Object value) {
        try {
            @SuppressWarnings("unchecked")
            T castedValue = (T) value;
            return schema.isValid(castedValue);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
