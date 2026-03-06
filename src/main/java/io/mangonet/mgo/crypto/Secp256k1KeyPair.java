package io.mangonet.mgo.crypto;

import io.mangonet.mgo.crypto.exceptions.MnemonicsException;
import io.mangonet.mgo.crypto.exceptions.SigningException;
import io.mangonet.mgo.crypto.signature.SignatureScheme;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;
import java.util.List;

public class Secp256k1KeyPair extends MgoKeyPair<ECKey> {

    private final static String DEFAULT_ED25519_DERIVATION_PATH = "m/54'/938'/0'/0/0";

    public Secp256k1KeyPair(byte[] privateKey) {
        this.keyPair = ECKey.fromPrivate(privateKey);
    }

    public Secp256k1KeyPair(String privateKey) {
        this(Hex.decode(privateKey));
    }

    @Override
    public byte[] publicKeyBytes() {
        return keyPair.getPubKey();
    }

    @Override
    public SignatureScheme signatureScheme() {
        return SignatureScheme.SECP256K1;
    }

    @Override
    public byte[] sign(byte[] msg) throws SigningException {
        try {
            Sha256Hash sha256Hash = Sha256Hash.of(msg);
            ECKey.ECDSASignature signature = keyPair.sign(sha256Hash);

            byte[] sigData = new byte[64]; // 32 bytes for R + 32 bytes for S
            System.arraycopy(Utils.bigIntegerToBytes(signature.r, 32), 0, sigData, 0, 32);
            System.arraycopy(Utils.bigIntegerToBytes(signature.s, 32), 0, sigData, 32, 32);
            return sigData;
        } catch (RuntimeException e) {
            throw new SigningException("Mgo wallet signature failed by Secp256k1.");
        }
    }

    @Override
    public String encodePrivateKey() {
        return Hex.toHexString(this.keyPair.getPrivKeyBytes());
    }

    @Override
    public byte[] privateKey() {
        return this.keyPair.getPrivKeyBytes();
    }

    /**
     * Decode hex mgo key pair.
     *
     * @param encoded the encoded
     * @return the mgo key pair
     */
    public static Secp256k1KeyPair decodeHex(String encoded) {
        return decode(Hex.decode(encoded));
    }

    /**
     * Decode bytes mgo key pair.
     *
     * @param seed the bytes
     * @return the mgo key pair
     */
    public static Secp256k1KeyPair decode(byte[] seed) {
        return new Secp256k1KeyPair(Arrays.copyOfRange(seed, 0, seed.length));
    }

    /**
     * Derive Secp256k1 keypair from mnemonics and path. The mnemonics must be normalized
     * and validated against the english wordlist.
     *
     * If path is none, it will default to m/54'/938'/0'/0/0, otherwise the path must
     * be compliant to BIP-32 in form m/54'/938'/{account_index}'/{change_index}/{address_index}.
     * @param mnemonics
     * @param path
     * @return
     */
    public static Secp256k1KeyPair deriveKeypair(String mnemonics, String path) {
        if (path == null) {
            path = DEFAULT_ED25519_DERIVATION_PATH;
        }

        if (!Mnemonics.isValidBIP32Path(path)) {
            throw new MnemonicsException("Invalid derivation path");
        }
        try {
            DeterministicSeed seed = new DeterministicSeed(mnemonics, null, "", 0L);
            DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
            List<ChildNumber> pathList = Mnemonics.parsePath(path);
            DeterministicKey key = chain.getKeyByPath(pathList, true);

            return new Secp256k1KeyPair(key.getPrivKeyBytes());
        } catch (Exception e) {
            throw new MnemonicsException("Failed to derive Secp256k1 keypair", e);
        }
    }

    /**
     * Generate Secp256k1 mgo key pair.
     *
     * @return the mgo key pair
     */
    public static Secp256k1KeyPair generate() {
        byte[] seed = new byte[32];
        SECURE_RANDOM.nextBytes(seed);
        return new Secp256k1KeyPair(seed);
    }

}
