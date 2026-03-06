package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.mangonet.mgo.model.mgo.MoveCallMgoTransaction;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import io.mangonet.mgo.model.mgo.kind.arg.GasCoin;
import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import io.mangonet.mgo.model.transaction.kind.operate.*;
import io.mangonet.mgo.model.transaction.kind.operate.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MgoTransactionDeserializer extends StdDeserializer<MgoTransaction> {

    public MgoTransactionDeserializer() {
        super(MgoTransaction.class);
    }

    @Override
    public MgoTransaction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode root = mapper.readTree(p);

        if (root.has("MoveCall")) {
            return deserializeMoveCall(root, mapper);
        } else if (root.has("TransferObjects")) {
            return deserializeTransferObjects(root, mapper);
        } else if (root.has("SplitCoins")) {
            return deserializeSplitCoins(root, mapper);
        } else if (root.has("MergeCoins")) {
            return deserializeMergeCoins(root, mapper);
        } else if (root.has("Publish")) {
            return deserializePublish(root, mapper);
        } else if (root.has("Upgrade")) {
            return deserializeUpgrade(root, mapper);
        } else if (root.has("MakeMoveVec")) {
            return deserializeMakeMoveVec(root, mapper);
        } else {
            throw ctxt.instantiationException(MgoTransaction.class,
                    "Unknown transaction type, must be one of: MoveCall, TransferObjects, SplitCoins, MergeCoins, Publish, Upgrade, MakeMoveVec");
        }
    }

    private MoveCall deserializeMoveCall(ObjectNode node, ObjectMapper mapper) {
        return new MoveCall(mapper.convertValue(node.get("MoveCall"), MoveCallMgoTransaction.class));
    }

    private TransferObjects deserializeTransferObjects(ObjectNode node, ObjectMapper mapper) {
        ArrayNode transferObjectsArray = (ArrayNode) node.get("TransferObjects");

        if (transferObjectsArray.size() != 2) {
            throw new IllegalArgumentException("TransferObjects must have exactly 2 elements");
        }

        TransferObjects.TransferObjectsData data = new TransferObjects.TransferObjectsData();
        // Handle first element (objects)
        data.setObjects(this.parseMgoArgumentList(transferObjectsArray.get(0), mapper));

        // Handle second element (address)
        data.setAddress(this.parseMgoArgument(transferObjectsArray.get(1), mapper));

        return new TransferObjects(data);
    }

    private SplitCoins deserializeSplitCoins(ObjectNode node, ObjectMapper mapper) {
        ArrayNode splitCoinsArray = (ArrayNode) node.get("SplitCoins");

        if (splitCoinsArray.size() != 2) {
            throw new IllegalArgumentException("SplitCoins must have exactly 2 elements");
        }

        SplitCoins.SplitCoinsData data = new SplitCoins.SplitCoinsData();

        // Handle first element (coin)
        data.setCoin(this.parseMgoArgument(splitCoinsArray.get(0), mapper));

        // Handle second element (amounts)
        data.setAmounts(this.parseMgoArgumentList(splitCoinsArray.get(1), mapper));

        return new SplitCoins(data);
    }

    private MergeCoins deserializeMergeCoins(ObjectNode node, ObjectMapper mapper) {
        ArrayNode mergeCoinsArray = (ArrayNode) node.get("MergeCoins");

        if (mergeCoinsArray.size() != 2) {
            throw new IllegalArgumentException("MergeCoins must have exactly 2 elements");
        }

        MergeCoins.MergeCoinsData data = new MergeCoins.MergeCoinsData();

        // handle first element (destination)
        data.setDestination(this.parseMgoArgument(mergeCoinsArray.get(0), mapper));

        // handle second element (sources)
        data.setSources(this.parseMgoArgumentList(mergeCoinsArray.get(1), mapper));

        return new MergeCoins(data);
    }

    private Publish deserializePublish(ObjectNode node, ObjectMapper mapper) {
        ArrayNode publishNode = (ArrayNode) node.get("Publish");

        List<String> packageBytes = new ArrayList<>();
        publishNode.forEach(n -> packageBytes.add(n.asText()));

        return new Publish(packageBytes);
    }

    private Upgrade deserializeUpgrade(ObjectNode node, ObjectMapper mapper) {
        ArrayNode upgradeNode = (ArrayNode) node.get("Upgrade");

        if (upgradeNode.size() != 3) {
            throw new IllegalArgumentException("Upgrade must have exactly 3 elements");
        }

        Upgrade.UpgradeData data = new Upgrade.UpgradeData();

        // First element: array of strings (package bytes)
        ArrayNode packageBytesNode = (ArrayNode) upgradeNode.get(0);
        List<String> packageBytes = new ArrayList<>();
        packageBytesNode.forEach(n -> packageBytes.add(n.asText()));
        data.setPackageBytes(packageBytes);

        // Second element: string (package ID)
        data.setPackageId(upgradeNode.get(1).asText());

        // Third element: MgoArgument (upgrade ticket)
        data.setUpgradeTicket(this.parseMgoArgument(upgradeNode.get(2), mapper));

        return new Upgrade(data);
    }

    private MakeMoveVec deserializeMakeMoveVec(ObjectNode node, ObjectMapper mapper) {
        ArrayNode makeMoveVecArray = (ArrayNode) node.get("MakeMoveVec");

        if (makeMoveVecArray.size() != 2) {
            throw new IllegalArgumentException("MakeMoveVec must have exactly 2 elements");
        }

        MakeMoveVec.MakeMoveVecData data = new MakeMoveVec.MakeMoveVecData();

        // First element: string or null (type tag)
        if (makeMoveVecArray.get(0).isNull()) {
            data.setTypeTag(null);
        } else {
            data.setTypeTag(makeMoveVecArray.get(0).asText());
        }

        // Second element: array of MgoArgument (elements)
        data.setElements(this.parseMgoArgumentList(makeMoveVecArray.get(1), mapper));

        return new MakeMoveVec(data);
    }

    /**
     * Helper method: parse MgoArgument list
     * @param node
     * @param mapper
     * @return
     * @throws IOException
     */
    private List<MgoArgument> parseMgoArgumentList(JsonNode node, ObjectMapper mapper) {
        List<MgoArgument> amounts = new ArrayList<>();

        if (!node.isArray()) {
            throw new IllegalArgumentException("Second element of SplitCoins must be an array");
        }
        for (JsonNode item : node) {
            amounts.add(this.parseMgoArgument(item, mapper));
        }
        return amounts;
    }

    /**
     * Helper method: parse MgoArgument
     * @param node
     * @param mapper
     * @return
     * @throws IOException
     */
    private MgoArgument parseMgoArgument(JsonNode node, ObjectMapper mapper) {
        try {
            if (node.isTextual() && "GasCoin".equals(node.asText())) {
                return new GasCoin();
            } else if (node.isObject()) {
                // Use the registered custom deserializer.
                return mapper.treeToValue(node, MgoArgument.class);
            }
            throw new IllegalArgumentException("Unsupported argument type: " + node.getNodeType());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    "BridgeStateCreate must be a string or object or array (MgoArgument), cause: " + e.getMessage()
            );
        }
    }
}
