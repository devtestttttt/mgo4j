package io.mangonet.mgo.crypto;

import org.bitcoinj.core.ECKey;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Ed25519KeyPairTest {

    // Test with a fixed key (Base64-encoded 32-byte private key)
    private static final String VALID_BASE64_KEY =
            "f60a65eb1a75838674ecc613a0c8d28ab49635cc2bd7697f1b0019193dc2f148";
    // Replace with the actual computed address
    private static final String EXPECTED_ADDRESS =
            "0xf9d30ef50a775f019a80dc46be0ced7c7c86f19089f4a29f71fce522a832ceac";
    private static final String TEST_MESSAGE = "hello_mgo";

    @Test
    void createMgoKeyPair() {
        // When
        MgoKeyPair<AsymmetricCipherKeyPair> keyPair = Ed25519KeyPair.generate();
        String address = keyPair.address();
        String key = keyPair.encodePrivateKey();
        System.out.println("Ed25519     key : " + key);
        System.out.println("Ed25519     key length : " + Hex.decode(key).length);
        System.out.println("Ed25519     public key : " + Base64.toBase64String(keyPair.publicKeyBytes()));
        System.out.println("Ed25519     public publicKeyBytes length : " + Base64.toBase64String(keyPair.publicKeyBytes()).length());
        System.out.println("Ed25519     public key length : " + keyPair.publicKeyBytes().length);
        System.out.println("Ed25519 address : " + address);
        System.out.println(" ---------------------------------------------------");

        // When
        MgoKeyPair<ECKey> key256Pair = Secp256k1KeyPair.generate();
        String address256 = key256Pair.address();
        String key256 = key256Pair.encodePrivateKey();
        System.out.println("Secp256k1     key : " + key256);
        System.out.println("Secp256k1     key length : " + Hex.decode(key256).length);
        System.out.println("Secp256k1     public key : " + Base64.toBase64String(key256Pair.publicKeyBytes()));
        System.out.println("Secp256k1     public publicKeyBytes length : " + Base64.toBase64String(key256Pair.publicKeyBytes()).length());
        System.out.println("Secp256k1     public key length : " + key256Pair.publicKeyBytes().length);
        System.out.println("Secp256k1 address : " + address256);

        // Then
        assertThat(keyPair).isNotNull();
        assertThat(keyPair.getKeyPair()).isNotNull();
        assertThat(Hex.decode(key))
                .isNotNull()
                .hasSize(32);
        assertThat(address).isNotNull()
                .startsWith("0x")
                .hasSize(66);
    }

    @Test
    void createMgoKeyDerive() {
        // When
        String seed = Mnemonics.generateMnemonics();
        Ed25519KeyPair keyPair = Ed25519KeyPair.deriveKeypair(seed, null);
        String address = keyPair.address();
        System.out.println("Ed25519    seed : " + seed);
        System.out.println("Ed25519 address : " + address);
        System.out.println(" ---------------------------------------------------");

        // Then
        assertThat(keyPair).isNotNull();
        assertThat(keyPair.getKeyPair()).isNotNull();
        assertThat(address).isNotNull()
                .startsWith("0x")
                .hasSize(66);
    }

    @Test
    void importMgoKeyDerive() {
        // 0xc32902d3877349fdfc25bb65ff15b1d619f84fdd57d20080ece4fdcd42b99849
        // When
        String mgoKeyPair = "mgoprivkey1qqqhlsg907z7lz0qt2jtna9m7ztm0hzcv0df302gkpt3pu8gutmagkpcv75";
        MgoKeyPair keyPair = Ed25519KeyPair.decodeMgoPrivateKey(mgoKeyPair);
        String address = keyPair.address();
        System.out.println("Ed25519 address : " + address);
        System.out.println(" ---------------------------------------------------");

        // Then
        assertThat(keyPair).isNotNull();
        assertThat(keyPair.getKeyPair()).isNotNull();
        assertThat(address).isNotNull()
                .startsWith("0x")
                .hasSize(66);
        assertThat(address).isEqualTo("0xc32902d3877349fdfc25bb65ff15b1d619f84fdd57d20080ece4fdcd42b99849");
    }

    @Test
    void decodeBase64_shouldWorkWithValidKey() {
        // When
        Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(VALID_BASE64_KEY);

        // Then
        assertThat(keyPair).isNotNull();
        assertThat(keyPair.getKeyPair()).isInstanceOf(AsymmetricCipherKeyPair.class);
    }

    @Test
    void address_shouldGenerateCorrectFormat() {
        // Given
        Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(VALID_BASE64_KEY);

        // When
        String address = keyPair.address();

        // Then
        assertThat(address)
                .startsWith("0x")
                .hasSize(66) // 0x + 32-byte hex
                .isEqualTo(EXPECTED_ADDRESS); // Precomputed verification values are required.
    }

    @Test
    void encodePrivateKey_shouldReturnOriginalHex() {
        // Given
        Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(VALID_BASE64_KEY);

        // When
        String encoded = keyPair.encodePrivateKey();

        // Then
        assertThat(encoded)
                .isEqualTo(VALID_BASE64_KEY) // The Base64 decoded output should match the original input.
                .hasSize(64); // 32-byte hexadecimal representation.
    }

    @Test
    void publicKeyBytes_shouldReturn32Bytes() {
        // Given
        Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(VALID_BASE64_KEY);

        // When
        byte[] pubKey = keyPair.publicKeyBytes();

        // Then
        assertThat(pubKey)
                .hasSize(32)
                .isEqualTo(((Ed25519PublicKeyParameters)keyPair.getKeyPair().getPublic()).getEncoded());
    }

    @Test
    void sign_shouldGenerateValidSignature() {
        // Given
        Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(VALID_BASE64_KEY);
        byte[] message = TEST_MESSAGE.getBytes();

        // When
        byte[] signature = keyPair.sign(message);

        // Then
        assertThat(signature)
                .hasSize(64) // Ed25519 signature is fixed at 64 bytes.
                .isNotEqualTo(message); // The signature should differ from the original message.
    }

//    @Test
//    void sign_shouldThrowOnEmptyMessage() {
//        // Given
//        Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(VALID_BASE64_KEY);
//
//        // Then
//        assertThrows(SigningException.class, () -> keyPair.sign(new byte[0]));
//    }

//    @Test
//    void decodeBase64_shouldThrowOnInvalidKey() {
//        // Invalid key cases
//        String[] invalidKeys = {
//                "",                          // null string
//                "123",                       // too short
//                VALID_BASE64_KEY + "00",     // too long
//                "6f1c5cc83e7fdb63a163a82eb9a25d1fb8eb72873904ecf0b838fb1fd2f629xx" // Non-hex characters
//        };
//
//        for (String invalidKey : invalidKeys) {
//            assertThatThrownBy(() -> Ed25519KeyPair.decodeHex(invalidKey))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//    }

    // Boundary testing: All-zero and all-F keys
    @Test
    void boundaryKeyTests() {
        String[] boundaryKeys = {
                "0000000000000000000000000000000000000000000000000000000000000000",
                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
        };

        for (String key : boundaryKeys) {
            Ed25519KeyPair keyPair = (Ed25519KeyPair) Ed25519KeyPair.decodeHex(key);
            assertThat(keyPair.address()).isNotNull();
            assertThat(keyPair.sign(TEST_MESSAGE.getBytes())).hasSize(64);
        }
    }
}
