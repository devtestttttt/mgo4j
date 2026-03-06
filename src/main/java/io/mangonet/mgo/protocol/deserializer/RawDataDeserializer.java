package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.move.kind.RawData;
import io.mangonet.mgo.model.move.kind.rawdata.MoveObjectRaw;
import io.mangonet.mgo.model.move.kind.rawdata.PackageRaw;

import java.io.IOException;

public class RawDataDeserializer extends JsonDeserializer<RawData> {

    @Override
    public RawData deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = p.getCodec().readTree(p);

        if (!node.has("dataType")) {
            throw new IllegalArgumentException("Missing dataType field in RawData");
        }

        String dataType = node.get("dataType").asText();

        switch (dataType) {
            case "moveObject":
                return mapper.treeToValue(node, MoveObjectRaw.class);
            case "package":
                return mapper.treeToValue(node, PackageRaw.class);
            default:
                throw new IllegalArgumentException("Unknown RawData type: " + dataType);
        }
    }

}
