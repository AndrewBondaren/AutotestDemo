package org.demo.module;

import lombok.Getter;


public class ConversionException extends RuntimeException {

    private static final String RESPONSE_ERROR = "Сервер вернул ошибку: %s";

    @Getter
    private final Object error;

    ConversionException(final Object error) {
        super(String.format(RESPONSE_ERROR, error.toString()));
        this.error = error;
    }

}
