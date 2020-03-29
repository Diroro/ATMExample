package Exceptions;

public class InvalidValueException extends IllegalArgumentException {
    public InvalidValueException(String text) {
        super(text);
    }
}
