package utils;

public class DeserializationException extends RuntimeException {

    DeserializationException(final String message) {
        super(message);
    }

    DeserializationException(final Throwable cause) {
        super(cause);
    }

    DeserializationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
