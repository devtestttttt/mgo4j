package io.mangonet.mgo.protocol.rx;

public interface Callback<T> {

    /**
     * Event handling
     * @param value
     */
    void onEvent(T value);

    /**
     * Error handling
     * @param throwable
     */
    void onError(Throwable throwable);

}
