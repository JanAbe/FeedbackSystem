package util.validators;

import domain.university.University;
import util.exceptions.EmptyOptionalException;
import util.exceptions.IllegalDateException;

import java.io.OptionalDataException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

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

    /**
     * <p>Validate that the given text is not blank.
     * If it is blank, an IllegalArgumentException is thrown with the provided message.</p>
     * @param text String
     * @param message String
     */
    public static void notBlank(String text, String message) {
        if (text.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the given collection is not empty.
     * If it is empty, an IllegalArgumentException is thrown with the provided message.</p>
     * @param collection Collection
     * @param message String
     */
    public static void notEmpty(Collection collection, String message) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void notEmpty(Optional<T> optional, String message) {
        if (optional.isEmpty()) {
            throw new EmptyOptionalException(message);
        }
    }
}
