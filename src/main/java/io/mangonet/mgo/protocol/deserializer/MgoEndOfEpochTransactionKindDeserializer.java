package io.mangonet.mgo.protocol.deserializer;

import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import io.mangonet.mgo.model.transaction.kind.epoch.*;
import io.mangonet.mgo.model.transaction.kind.epoch.*;
import io.mangonet.mgo.model.transaction.kind.epoch.subtypes.MgoAuthenticatorStateExpire;
import io.mangonet.mgo.model.transaction.kind.epoch.subtypes.MgoChangeEpoch;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;

public class MgoEndOfEpochTransactionKindDeserializer extends StdDeserializer<MgoEndOfEpochTransactionKind> {

    private static final Set<String> STRING_TYPES = Set.of(
            "AuthenticatorStateCreate",
            "RandomnessStateCreate",
            "CoinDenyListStateCreate",
            "StoreExecutionTimeObservations"
    );

    public MgoEndOfEpochTransactionKindDeserializer() {
        super(MgoEndOfEpochTransactionKind.class);
    }

    @Override
    public MgoEndOfEpochTransactionKind deserialize(
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        JsonNode root = p.getCodec().readTree(p);

        // Case 1: String type
        if (root.isTextual()) {
            return handleStringType(root.asText(), ctxt);
        }

        // Case 2: Object type
        if (root.isObject()) {
            if (root.has("ChangeEpoch")) {
                return handleChangeEpoch(root, p.getCodec());
            }
            if (root.has("AuthenticatorStateExpire")) {
                return handleAuthenticatorStateExpire(root, p.getCodec());
            }
            if (root.has("BridgeStateCreate")) {
                return handleBridgeStateCreate(root, p);
            }
            if (root.has("BridgeCommitteeUpdate")) {
                return handleBridgeCommitteeUpdate(root, p);
            }
        }

        throw ctxt.instantiationException(
                handledType(),
                "Invalid MgoEndOfEpochTransactionKind format. Expected: " +
                        "(1) String enum: " + STRING_TYPES + " OR " +
                        "(2) Object with one of [ChangeEpoch, AuthenticatorStateExpire, BridgeStateCreate, BridgeCommitteeUpdate]"
        );
    }

    // ========== Specific type handling method ==========

    private MgoEndOfEpochTransactionKind handleStringType(
            String type,
            DeserializationContext ctxt
    ) throws JsonProcessingException {
        if (!STRING_TYPES.contains(type)) {
            throw ctxt.instantiationException(
                    handledType(),
                    "Invalid string type. Allowed: " + STRING_TYPES
            );
        }
        return StringEndOfEpoch.of(type);
    }

    private MgoEndOfEpochTransactionKind handleChangeEpoch(
            JsonNode root,
            ObjectCodec codec
    ) throws JsonProcessingException {
        return new ChangeEpoch()
                .setChangeEpoch(codec.treeToValue(
                        root.get("ChangeEpoch"),
                        MgoChangeEpoch.class
                ));
    }

    private MgoEndOfEpochTransactionKind handleAuthenticatorStateExpire(
            JsonNode root,
            ObjectCodec codec
    ) throws JsonProcessingException {
        return new AuthenticatorStateExpire()
                .setAuthStateExpire(codec.treeToValue(
                        root.get("AuthenticatorStateExpire"),
                        MgoAuthenticatorStateExpire.class
                ));
    }

    private MgoEndOfEpochTransactionKind handleBridgeStateCreate(
            JsonNode root,
            JsonParser p
    ) throws JsonProcessingException {
        JsonNode node = root.get("BridgeStateCreate");
        if (!node.isTextual()) {
            throw new JsonParseException(
                    p,
                    "BridgeStateCreate must be a string (CheckpointDigest)"
            );
        }
        return new BridgeStateCreate()
                .setCheckpointDigest(node.asText());
    }

    private MgoEndOfEpochTransactionKind handleBridgeCommitteeUpdate(
            JsonNode root,
            JsonParser p
    ) throws JsonProcessingException {
        JsonNode node = root.get("BridgeCommitteeUpdate");
        if (!node.isTextual()) {
            throw new JsonParseException(
                    p,
                    "BridgeCommitteeUpdate must be a string (SequenceNumber2)"
            );
        }
        return new BridgeCommitteeUpdate()
                .setSequenceNumber(new BigInteger(node.asText()));
    }

}
