package io.mangonet.mgo.crypto.exceptions;

public class SignatureSchemeNotSupportedException extends RuntimeException {

    public SignatureSchemeNotSupportedException() {
        super("Signature scheme is not support!");
    }

}
