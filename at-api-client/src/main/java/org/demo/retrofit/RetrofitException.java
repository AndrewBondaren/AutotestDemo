package org.demo.module;

import lombok.Getter;

import java.io.IOException;


public class RetrofitException extends RuntimeException {

    @Getter
    private final Object error;

    RetrofitException(final Throwable exception) {
        super(exception.getMessage(), exception);

        if (exception instanceof ConversionException) {
            this.error = ((ConversionException) exception).getError();
        } else {
            error = exception.getMessage();
        }

    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }


}
