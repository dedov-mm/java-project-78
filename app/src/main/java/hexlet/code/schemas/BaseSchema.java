package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    /**
     * Adds a new check to the schema.
     *
     * @param name     the name of the check (used as an identifier)
     * @param validate the predicate that represents the validation logic
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Marks the schema as required.
     *
     * @return the schema itself for method chaining
     */
    public BaseSchema<T> required() {
        this.required = true;
        addCheck("required", value -> value != null);
        return this;
    }

    /**
     * Validates the given value against all defined checks.
     *
     * @param value the value to validate
     * @return true if all checks pass, false otherwise
     */
    public boolean isValid(T value) {
        for (Map.Entry<String, Predicate<T>> entry : checks.entrySet()) {
            if (!entry.getValue().test(value)) {
                return false;
            }
        }
        return true;
    }
}
