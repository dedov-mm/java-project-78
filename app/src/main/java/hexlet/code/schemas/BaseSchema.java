package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required = false;

    @SuppressWarnings("checkstyle")
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    @SuppressWarnings("checkstyle")
    protected boolean isRequired() {
        return required;
    }

    @SuppressWarnings("checkstyle")
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (required && !isValidType(value)) {
            return false;
        }
        return validate((T) value);
    }

    @SuppressWarnings("checkstyle")
    protected boolean isValidType(Object value) {
        return true;
    }

    protected abstract boolean validate(T value);
}
