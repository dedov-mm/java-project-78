package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<? super T>> checks = new LinkedHashMap<>();

    public final BaseSchema<T> addCheck(String name, Predicate<? super T> check) {
        checks.put(name, check);
        return this;
    }

    @SuppressWarnings("unchecked")
    public final boolean isValid(Object value) {
        T castedValue;
        try {
            castedValue = (T) value;
        } catch (ClassCastException e) {
            return false;
        }

        for (Predicate<? super T> check : checks.values()) {
            if (!check.test(castedValue)) {
                return false;
            }
        }
        return true;
    }
}
