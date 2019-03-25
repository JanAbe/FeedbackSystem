package validators;

public final class Validate {

    private Validate() {}

    /**
     * <p>Validate that the given object is not null.
     * Otherwise a NullPointerException is thrown with the provided message.</p>
     * @param object Object
     * @param message String
     * @throws NullPointerException
     */
    public static void notNull(Object object, String message) throws NullPointerException {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    /**
     * <p>Validate that the given argument is not null.
     * Otherwise an IllegalArgumenException is thrown with the provided message.</p>
     * @param object Object
     * @param message String
     * @throws IllegalArgumentException
     */
    public static void argumentNotNull(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
