package io.mangonet.mgo.protocol.deserializer;

import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import io.mangonet.mgo.protocol.DeserializerTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MgoTransactionDeserializerTest extends DeserializerTest {

    /**
     * Test MakeMoveVec
     * @throws Exception
     */
    @Test
    void shouldDeserializeMakeMoveVec() throws Exception {
        // Prepare test data
        String json1 = "{\"MakeMoveVec\":[\"0x1::coin::Coin<0x1::mgo::MGO>\",[{\"Input\":0},{\"Result\":1},{\"NestedResult\":[1,0]}]]}";
        String json2 = "{\"MakeMoveVec\":[null,[{\"Input\":2},{\"Result\":2}]]}";
        String json3 = "{\"MakeMoveVec\":[\"vector<u8>\",[]]}";
        List<String> jsons = Arrays.asList(json1, json2, json3);

        for (String json : jsons) {
            System.out.println("\njson = " + json);
            // Execute deserialization
            MgoTransaction transaction = mapper.readValue(json, MgoTransaction.class);

            // Verify results
            assertNotNull(transaction, "Deserialized object should not be null");
            // Add more assertions here based on actual MgoTransaction structure
            // assertEquals("GasCoin", transaction.getCoinType());
            // assertNotNull(transaction.getArguments());
            // assertEquals(1, transaction.getArguments().size());
            System.out.println(transaction);
        }
    }

    /**
     * Test MergeCoins
     * @throws Exception
     */
    @Test
    void shouldDeserializeMergeCoins() throws Exception {
        // Prepare test data
        String json1 = "{\"MergeCoins\": [\"GasCoin\", [{\"Input\": 0}]]}";
        String json2 = "{\"MergeCoins\": [\"GasCoin\", [{\"Input\": 0},{\"Result\": 0}]]}";
        String json3 = "{\"MergeCoins\": [\"GasCoin\", [{\"Input\": 0},{\"NestedResult\": [1,2]}]]}";

        List<String> jsons = Arrays.asList(json1, json2, json3);

        for (String json : jsons) {
            System.out.println("\njson = " + json);
            // Execute deserialization
            MgoTransaction transaction = mapper.readValue(json, MgoTransaction.class);

            // Verify results
            assertNotNull(transaction, "Deserialized object should not be null");
            // Add more assertions here based on actual MgoTransaction structure
            // assertEquals("GasCoin", transaction.getCoinType());
            // assertNotNull(transaction.getArguments());
            // assertEquals(1, transaction.getArguments().size());
            System.out.println(transaction);
        }
    }

    /**
     * test Publish
     * @throws Exception
     */
    @Test
    void shouldDeserializePublish() throws Exception {
        // Prepare test data
        String json1 = "{\"Publish\": [\"xxx\"]}";
        String json2 = "{\"Publish\": [\"xxx\", \"yyy\"]}";
        String json3 = "{\"Publish\": [\"xxx\", \"yyy\", \"zzzzzzz\"]}";

        List<String> jsons = Arrays.asList(json1, json2, json3);

        for (String json : jsons) {
            System.out.println("\njson = " + json);
            // Execute deserialization
            MgoTransaction transaction = mapper.readValue(json, MgoTransaction.class);

            // Verify results
            assertNotNull(transaction, "Deserialized object should not be null");
            // Add more assertions here based on actual MgoTransaction structure
            // assertEquals("GasCoin", transaction.getCoinType());
            // assertNotNull(transaction.getArguments());
            // assertEquals(1, transaction.getArguments().size());
            System.out.println(transaction);
        }
    }

    /**
     * test SplitCoins
     * @throws Exception
     */
    @Test
    void shouldDeserializeSplitCoins() throws Exception {
        // Prepare test data
        String json1 = "{\"SplitCoins\": [\"GasCoin\", [{\"Input\": 0}]]}";
        String json2 = "{\"SplitCoins\": [{\"Input\": 0}, [{\"Input\": 0},{\"Result\": 0}]]}";
        String json3 = "{\"SplitCoins\": [\"GasCoin\", [{\"Input\": 0},{\"NestedResult\": [1,2]}]]}";

        List<String> jsons = Arrays.asList(json1, json2, json3);

        for (String json : jsons) {
            // Prepare test data
            System.out.println("\njson = " + json);
            // Execute deserialization
            MgoTransaction transaction = mapper.readValue(json, MgoTransaction.class);

            // Verify results
            assertNotNull(transaction, "Deserialized object should not be null");
            // Add more assertions here based on actual MgoTransaction structure
            // assertEquals("GasCoin", transaction.getCoinType());
            // assertNotNull(transaction.getArguments());
            // assertEquals(1, transaction.getArguments().size());
            System.out.println(transaction);
        }
    }

    /**
     * test TransferObjects
     * @throws Exception
     */
    @Test
    void shouldDeserializeTransferObjects() throws Exception {
        // Prepare test data
        String json1 = "{\"TransferObjects\": [[{\"Input\": 0}], \"GasCoin\"]}";
        String json2 = "{\"TransferObjects\":[[{\"NestedResult\":[0,0]}], {\"Input\":0}]}";
        String json3 = "{\"TransferObjects\": [[{\"Input\": 0},{\"NestedResult\": [1,2]}], \"GasCoin\"]}";

        List<String> jsons = Arrays.asList(json1, json2, json3);

        for (String json : jsons) {
            // Prepare test data
            System.out.println("\njson = " + json);
            // Execute deserialization
            MgoTransaction transaction = mapper.readValue(json, MgoTransaction.class);

            // Verify results
            assertNotNull(transaction, "Deserialized object should not be null");
            // Add more assertions here based on actual MgoTransaction structure
            // assertEquals("GasCoin", transaction.getCoinType());
            // assertNotNull(transaction.getArguments());
            // assertEquals(1, transaction.getArguments().size());
            System.out.println(transaction);
        }
    }

    /**
     * test Upgrade
     * @throws Exception
     */
    @Test
    void shouldDeserializeUpgrade() throws Exception {
        // Prepare test data
        String json1 = "{\"Upgrade\":[[\"0x0000000000000000000000000000000000000001\",\"0x0000000000000000000000000000000000000002\"],\"0x2::mgo::MGO\",{\"Input\":1}]}";
        String json2 = "{\"Upgrade\":[[\"0x5d4b302506645c3f1f6a4e5a7315f5a5e5d5c5b5a5958575655545352515049\",\"0x0123456789abcdef0123456789abcdef01234567\",\"0x89abcdef0123456789abcdef0123456789abcdef\"],\"0x3::token::Token\",{\"Result\":0}]}";
        String json3 = "{\"Upgrade\":[[\"0x1\",\"0x2\"],\"0x2::coin::Coin<0x2::mgo::MGO>\",\"GasCoin\"]}";

        List<String> jsons = Arrays.asList(json1, json2, json3);

        for (String json : jsons) {
            // Prepare test data
            System.out.println("\njson = " + json);
            // Execute deserialization
            MgoTransaction transaction = mapper.readValue(json, MgoTransaction.class);

            // Verify results
            assertNotNull(transaction, "Deserialized object should not be null");
            // Add more assertions here based on actual MgoTransaction structure
            // assertEquals("GasCoin", transaction.getCoinType());
            // assertNotNull(transaction.getArguments());
            // assertEquals(1, transaction.getArguments().size());
            System.out.println(transaction);
        }
    }
}
