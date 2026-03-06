package io.mangonet.mgo.crypto;

import org.bitcoinj.core.Bech32;

import java.util.ArrayList;
import java.util.List;

public class Bech32Words extends Bech32{

    /**
     * Convert 8-bit bytes into 5-bit "words" for Bech32 encoding.
     */
    public static byte[] toWords(byte[] data) {
        return convertBits(data, 8, 5, true);
    }

    /**
     * Convert 5-bit "words" back into 8-bit bytes for Bech32 decoding.
     */
    public static byte[] fromWords(byte[] words) {
        return convertBits(words, 5, 8, false);
    }

    /**
     * Core conversion logic from BIP-173.
     * @param data input bytes
     * @param fromBits input bit size
     * @param toBits output bit size
     * @param pad whether to pad last group
     */
    private static byte[] convertBits(byte[] data, int fromBits, int toBits, boolean pad) {
        int acc = 0;
        int bits = 0;
        int maxv = (1 << toBits) - 1;
        List<Byte> ret = new ArrayList<>();

        for (byte value : data) {
            int b = value & 0xFF;
            if ((b >> fromBits) != 0) {
                throw new IllegalArgumentException("Input value exceeds " + fromBits + "-bit size: " + b);
            }
            acc = (acc << fromBits) | b;
            bits += fromBits;
            while (bits >= toBits) {
                bits -= toBits;
                ret.add((byte) ((acc >> bits) & maxv));
            }
        }

        if (pad) {
            if (bits > 0) {
                ret.add((byte) ((acc << (toBits - bits)) & maxv));
            }
        } else if (bits >= fromBits || ((acc << (toBits - bits)) & maxv) != 0) {
            throw new IllegalArgumentException("Invalid padding in convertBits");
        }

        byte[] result = new byte[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            result[i] = ret.get(i);
        }
        return result;
    }

}
