package io.mangonet.mgo.crypto.signature;

public enum SignatureScheme {

    /** Ed25519 signature scheme. */
    ED25519((byte) 0x00),
    /** Secp256k1 signature scheme. */
    SECP256K1((byte) 0x01),
    /** Secp256r1 signature scheme. */
    SECP256R1((byte) 0x02),
    /** multisig signature scheme. */
    MULTISIG((byte) 0x03),
    /** zkLogin signature scheme. */
    ZKLOGIN((byte) 0x05),
    /** passkey signature scheme. */
    PASSKEY((byte) 0x06);


    private final byte scheme;

    SignatureScheme(byte scheme) {
        this.scheme = scheme;
    }

    public byte getScheme() {
        return scheme;
    }

    public static SignatureScheme findByScheme(byte scheme) {
        for (SignatureScheme signatureScheme : values()) {
            if (signatureScheme.getScheme() == (scheme)) {
                return signatureScheme;
            }
        }
        return null;
    }

}
