package org.demo.module;

public interface Envelope<T, E> {

    T getData();

    E getError();

    boolean isSuccess();
}
