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

    public final boolean isValid(T value) {
        for (Predicate<? super T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
