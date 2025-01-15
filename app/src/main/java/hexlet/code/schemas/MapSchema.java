package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addCheck("required", value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value != null && value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", map -> {
            if (!(map instanceof Map)) {
                return false;
            }

            Map<?, ?> castedMap = (Map<?, ?>) map;
            for (Map.Entry<String, ? extends BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object value = castedMap.get(key);

                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
