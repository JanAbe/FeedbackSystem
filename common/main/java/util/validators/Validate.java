package util.validators;

import util.exceptions.IllegalDateException;

import java.time.LocalDateTime;

public final class Validate {

    private Validate() {}

    /**
     * <p>Validate that the given object is not null.
     * If it is null a NullPointerException is thrown with the provided message.</p>
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
     * If it is null an IllegalArgumentException is thrown with the provided message.</p>
     * @param object Object
     * @param message String
     * @throws IllegalArgumentException
     */
    public static void argumentNotNull(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the given firstDate does not occur before the given secondDate.
     * If it does occur before the secondDate, an IllegalDateException is thrown with
     * the provided message</p>
     * @param firstDate LocalDateTime
     * @param secondDate LocalDateTime
     * @param message String
     * @throws IllegalDateException
     */
    public static void notBefore(LocalDateTime firstDate,
                                 LocalDateTime secondDate,
                                 String message) throws IllegalDateException {

        if (firstDate.isBefore(secondDate)) {
            throw new IllegalDateException(message);
        }
    }
}
