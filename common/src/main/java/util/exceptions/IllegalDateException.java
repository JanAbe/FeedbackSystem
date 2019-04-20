package util.exceptions;

public class IllegalDateException extends RuntimeException {

    public IllegalDateException() {}

    public IllegalDateException(String message) {
        super(message);
    }
}
