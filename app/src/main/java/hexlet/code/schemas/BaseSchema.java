package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required = false;

    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected boolean isRequired() {
        return required;
    }

    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (required && !isValidType(value)) {
            return false;
        }
        return validate((T) value);
    }

    protected boolean isValidType(Object value) {
        return true;
    }

    protected abstract boolean validate(T value);
}
