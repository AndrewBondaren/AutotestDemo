package org.demo.retrofit;

public interface Envelope<T, E> {

    T getData();

    E getError();

    boolean isSuccess();
}
