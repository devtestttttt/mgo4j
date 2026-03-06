package io.mangonet.mgo.crypto;

import io.mangonet.mgo.crypto.exceptions.MnemonicsException;
import io.mangonet.mgo.crypto.exceptions.SigningException;
import io.mangonet.mgo.crypto.signature.SignatureScheme;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.util.encoders.Hex;

public class Ed25519KeyPair extends MgoKeyPair<AsymmetricCipherKeyPair> {

    private final static String DEFAULT_ED25519_DERIVATION_PATH = "m/44'/938'/0'/0'/0'";

    public Ed25519KeyPair(byte[] privateKey) {
        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(privateKey, 0);
        Ed25519PublicKeyParameters publicKeyParameters = privateKeyParameters.generatePublicKey();
            this.keyPair = new AsymmetricCipherKeyPair(publicKeyParameters, privateKeyParameters);
    }

    public Ed25519KeyPair(
        Ed25519PrivateKeyParameters privateKeyParameters,
        Ed25519PublicKeyParameters publicKeyParameters) {
            this.keyPair = new AsymmetricCipherKeyPair(publicKeyParameters, privateKeyParameters);
    }

    @Override
    public byte[] publicKeyBytes() {
        return ((Ed25519PublicKeyParameters) keyPair.getPublic()).getEncoded();
    }

    @Override
    public SignatureScheme signatureScheme() {
        return SignatureScheme.ED25519;
    }

    @Override
    public byte[] sign(byte[] msg) throws SigningException {
        Signer signer = new Ed25519Signer();
        signer.init(true, keyPair.getPrivate());
        signer.update(msg, 0, msg.length);
        try {
            return signer.generateSignature();
        } catch (CryptoException e) {
            throw new SigningException("Mgo wallet signature failed by Ed25519.");
        }
    }

    @Override
    public String encodePrivateKey() {
        Ed25519PrivateKeyParameters pair = (Ed25519PrivateKeyParameters) this.keyPair.getPrivate();
        return Hex.toHexString(pair.getEncoded());
    }

    @Override
    public byte[] privateKey() {
        Ed25519PrivateKeyParameters pair = (Ed25519PrivateKeyParameters) this.keyPair.getPrivate();
        return pair.getEncoded();
    }

    /**
     * Decode hex mgo key pair.
     *
     * @param encoded the encoded
     * @return the mgo key pair
     */
    public static Ed25519KeyPair decodeHex(String encoded) {
        return decode(Hex.decode(encoded));
    }

    /**
     * Decode privateKey bytes mgo key pair.
     *
     * @param privateKey
     * @return
     */
    public static Ed25519KeyPair decode(byte[] privateKey) {
        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(privateKey, 0);
        Ed25519PublicKeyParameters publicKeyParameters = privateKeyParameters.generatePublicKey();
        return new Ed25519KeyPair(privateKeyParameters, publicKeyParameters);
    }

    /**
     * Derive Ed25519 keypair from mnemonics and path. The mnemonics must be normalized
     * and validated against the english wordlist.
     *
     * If path is none, it will default to m/44'/938'/0'/0'/0', otherwise the path must
     * be compliant to SLIP-0010 in form m/44'/938'/{account_index}'/{change_index}'/{address_index}'.
     * @param mnemonics
     * @param path
     * @return
     */
    public static Ed25519KeyPair deriveKeypair(String mnemonics, String path) {
        if (path == null) {
            path = DEFAULT_ED25519_DERIVATION_PATH;
        }

        if (!Mnemonics.isValidHardenedPath(path)) {
            throw new MnemonicsException("Invalid derivation path");
        }
        Ed25519KeyDerive derived = Ed25519KeyDerive.derivePath(path, Mnemonics.mnemonicToSeedHex(mnemonics));

        Ed25519PrivateKeyParameters privateKey = new Ed25519PrivateKeyParameters(derived.getKey(), 0);
        Ed25519PublicKeyParameters publicKey = privateKey.generatePublicKey();

        return new Ed25519KeyPair(privateKey, publicKey);
    }

    /**
     * Derive Ed25519 keypair from mnemonicSeed and path.
     *
     * If path is none, it will default to m/44'/938'/0'/0'/0', otherwise the path must
     * be compliant to SLIP-0010 in form m/44'/938'/{account_index}'/{change_index}'/{address_index}'.
     * @param seedHex
     * @param path
     * @return
     */
    public static Ed25519KeyPair deriveKeypairFromSeed(String seedHex, String path) {
        if (path == null) {
            path = DEFAULT_ED25519_DERIVATION_PATH;
        }

        if (!Mnemonics.isValidHardenedPath(path)) {
            throw new MnemonicsException("Invalid derivation path");
        }
        Ed25519KeyDerive derived = Ed25519KeyDerive.derivePath(path, seedHex);

        Ed25519PrivateKeyParameters privateKey = new Ed25519PrivateKeyParameters(derived.getKey(), 0);
        Ed25519PublicKeyParameters publicKey = privateKey.generatePublicKey();

        return new Ed25519KeyPair(privateKey, publicKey);
    }

    /**
     * Generate Ed25519 mgo key pair.
     *
     * @return the mgo key pair
     */
    public static Ed25519KeyPair generate() {
        byte[] seed = new byte[32];
        SECURE_RANDOM.nextBytes(seed);
        return new Ed25519KeyPair(seed);
    }
}
