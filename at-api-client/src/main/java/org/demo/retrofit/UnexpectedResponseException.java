package org.demo.retrofit;


public class UnexpectedResponseException extends RuntimeException {

    private static final String UNEXPECTED_ERROR = "Неожиданная ошибка при"
            + " выполнении запроса: %s";

    public UnexpectedResponseException(final String message) {
        super(String.format(UNEXPECTED_ERROR, message));
    }

    public UnexpectedResponseException(final Throwable cause) {
        super(String.format(UNEXPECTED_ERROR, cause.getMessage()), cause);
    }
}
