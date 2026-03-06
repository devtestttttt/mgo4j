package io.mangonet.mgo.protocol.exceptions;

public class RpcRequestFailedException extends RuntimeException{
    public RpcRequestFailedException(String message) {
        super(message);
    }

    public RpcRequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
