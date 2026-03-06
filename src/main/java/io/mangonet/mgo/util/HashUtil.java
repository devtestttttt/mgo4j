package io.mangonet.mgo.util;

import org.bitcoinj.core.Base58;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final MessageDigest digest;
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get sha256 result
     * @param message
     * @return
     */
    public static byte[] sha256(byte[] message) {
        return digest.digest(message);
    }

    /**
     * Get sha256 Base58 result
     * @param message
     * @return
     */
    public static String sha256Base58(byte[] message) {
        return Base58.encode(sha256(message));
    }

    /**
     * Get sha256 Base58 result
     * @param message
     * @return
     */
    public static String sha256Base58(String message) {
        return Base58.encode(sha256(message.getBytes(UTF8)));
    }
}
