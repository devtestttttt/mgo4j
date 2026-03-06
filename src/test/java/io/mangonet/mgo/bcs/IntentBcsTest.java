package io.mangonet.mgo.bcs;

import io.mangonet.mgo.crypto.Ed25519KeyPair;
import io.mangonet.mgo.protocol.MgoClientTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
public class IntentBcsTest extends MgoClientTest {

    // Test with a fixed key (Base64-encoded 32-byte private key).
    private static final String KEY =
            "f60a65eb1a75838674ecc613a0c8d28ab49635cc2bd7697f1b0019193dc2f148";

    @Test
    void testPersonalMessage() throws IOException {
//        String message = "123";
        String message = "000000000000006c6b935b8bbd4000000000000000000000016345785d8a000000000000000000008ac7230489e80000000000000000000000000197e39b9d70000000000000000032c42fef490b188931789797feb1125034ec8118a5d15ef927b9b60e07e2ca1e3a19c6f9b313c4de503b498a3f519462710c2ff8783a727c6db984735c1dbc8518446970436f696e";
        Ed25519KeyPair keyPair = Ed25519KeyPair.decodeHex(KEY);
        String signPersonalMessage = keyPair.signPersonalMessageBase64(message);
        log.info("PersonalMessage: {}\n -> {}", message, signPersonalMessage);
    }
}
