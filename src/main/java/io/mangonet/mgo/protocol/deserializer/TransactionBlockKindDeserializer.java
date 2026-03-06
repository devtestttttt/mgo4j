package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import io.mangonet.mgo.model.transaction.kind.block.*;
import io.mangonet.mgo.model.transaction.kind.block.*;

import java.io.IOException;
import java.util.Map;

public class TransactionBlockKindDeserializer extends StdDeserializer<TransactionBlockKind> {

    private static final String FIELD = "kind";

    private static final Map<String, Class<? extends TransactionBlockKind>> KIND_MAPPING =
            Map.ofEntries(
                    Map.entry("ChangeEpoch", ChangeEpoch.class),
                    Map.entry("Genesis", Genesis.class),
                    Map.entry("ConsensusCommitPrologue", ConsensusCommitPrologue.class),
                    Map.entry("ProgrammableTransaction", ProgrammableTransaction.class),
                    Map.entry("AuthenticatorStateUpdate", AuthenticatorStateUpdate.class),
                    Map.entry("RandomnessStateUpdate", RandomnessStateUpdate.class),
                    Map.entry("EndOfEpochTransaction", EndOfEpochTransaction.class),
                    Map.entry("ConsensusCommitPrologueV2", ConsensusCommitPrologueV2.class),
                    Map.entry("ConsensusCommitPrologueV3", ConsensusCommitPrologueV3.class),
                    Map.entry("ConsensusCommitPrologueV4", ConsensusCommitPrologueV4.class)
            );

    public TransactionBlockKindDeserializer() {
        super(TransactionBlockKind.class);
    }

    @Override
    public TransactionBlockKind deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        JsonNode root = p.getCodec().readTree(p);
        JsonNode kindNode = root.get(FIELD);

        if (kindNode == null) {
            throw ctxt.instantiationException(
                    TransactionBlockKind.class,
                    "Missing required field 'kind'"
            );
        }

        String kind = kindNode.asText();
        Class<? extends TransactionBlockKind> targetClass = KIND_MAPPING.get(kind);

        if (targetClass == null) {
            throw ctxt.instantiationException(
                    TransactionBlockKind.class,
                    "Unknown transaction kind: " + kind
            );
        }

        try {
            return p.getCodec().treeToValue(root, targetClass);
        } catch (JsonProcessingException e) {
            throw ctxt.instantiationException(
                    TransactionBlockKind.class,
                    "Failed to deserialize " + kind + ": " + e.getMessage()
            );
        }
    }
}
