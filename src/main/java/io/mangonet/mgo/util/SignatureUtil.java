package io.mangonet.mgo.util;

public class SignatureUtil {

    /**
     * get public key by signature
     * @param signature
     * @return
     */
    public static String getPublicKey(String signature) {
        if (signature == null || signature.isEmpty()) {
            return "";
        }
        int lastIndex = signature.length() - 44;
        return signature.substring(lastIndex);
    }

    /**
     * get single signature by signature
     * @param signature
     * @return
     */
    public static String getSignature(String signature) {
        if (signature == null || signature.isEmpty()) {
            return "";
        }
        int lastIndex = signature.length() - 44;
        return signature.substring(0, lastIndex);
    }
}
