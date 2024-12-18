package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required = false;

    /**
     * Marks this schema as required.
     * @return the updated schema
     */
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    /**
     * Indicates whether the schema is marked as required.
     * @return true if the schema is required
     */
    protected boolean isRequired() {
        return required;
    }

    /**
     * Checks if the given value satisfies the schema's constraints.
     * @param value the value to validate
     * @return true if the value is valid according to the schema
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (required && !isValidType(value)) {
            return false;
        }
        return validate((T) value);
    }

    /**
     * Checks if the given value is of a valid type for this schema.
     * @param value the value to check
     * @return true if the value is of a valid type
     */
    protected boolean isValidType(Object value) {
        return true;
    }

    protected abstract boolean validate(T value);
}
