package io.mangonet.mgo.util;

import org.bouncycastle.util.encoders.Hex;

public class ObjectIdUtil {

    private static final String HEX_PREFIX = "0x";

    /**
     * Normalize Mgo address
     * @param address
     * @return
     */
    public static String normalizeMgoAddress(String address) {
        if (address == null || address.isEmpty()) {
            return address;
        }

        // Remove 0x prefix (if exists)
        if (address.startsWith(HEX_PREFIX)) {
            address = address.substring(2);
        }

        // Ensure address length is 64 characters (32 bytes)
        if (address.length() < 64) {
            address = "0".repeat(64 - address.length()) + address;
        }

        return HEX_PREFIX + address;
    }

    /**
     * Convert to address
     * @param address
     * @return
     */
    public static String toAddress(byte[] address) {
        return HEX_PREFIX + Hex.toHexString(address);
    }

}
