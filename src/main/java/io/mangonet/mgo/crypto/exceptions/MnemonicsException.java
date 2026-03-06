package io.mangonet.mgo.crypto.exceptions;

public class MnemonicsException extends RuntimeException {

    public MnemonicsException(String message) {
        super(message);
    }

    public MnemonicsException(String message, Throwable cause) {
        super(message, cause);
    }

}
