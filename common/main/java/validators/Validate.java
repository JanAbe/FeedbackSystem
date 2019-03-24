package validators;

public final class Validate {

    private Validate() {}

    public static <T> void notNull(T object, String message) throws NullPointerException {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    public static <T> void argumentNotNull(T argument, String message) throws IllegalArgumentException {
        if (argument == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
