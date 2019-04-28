package common.validators;

import common.exceptions.EmptyOptionalException;
import common.exceptions.IllegalDateException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
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
    public static void notBlank(String text, String message) throws IllegalArgumentException {
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
    public static void notEmpty(Collection collection, String message) throws IllegalArgumentException {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the given optional is not empty.
     * If it is empty, an EmptyOptionalException is thrown with the provided message.</p>
     * @param optional Optional
     * @param message String
     * @param <T> T
     * @throws EmptyOptionalException
     */
    public static <T> void notEmpty(Optional<T> optional, String message) throws EmptyOptionalException {
        if (optional.isEmpty()) {
            throw new EmptyOptionalException(message);
        }
    }

    /**
     * <p>Validate that the given resource is not empty.
     * If it is empty, a WebApplicationException is thrown with status NOT FOUND.</p>
     * @param resource Optional
     * @param <T> T
     * @throws WebApplicationException
     */
    public static <T> void resourceNotAbsent(Optional<T> resource) throws WebApplicationException {
        if (resource.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    /**
     * <p>Validate that the given resources are not absent.
     * If they are absent, a WebApplicationException is thrown with status NOT FOUND.</p>
     * @param resources Collection
     * @throws WebApplicationException
     */
    public static void resourceNotAbsent(Collection resources) throws WebApplicationException {
        if (resources == null || resources.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
