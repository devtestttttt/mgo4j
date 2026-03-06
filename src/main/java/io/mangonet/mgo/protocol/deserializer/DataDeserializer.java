package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.move.kind.Data;
import io.mangonet.mgo.model.move.kind.data.MoveObject;
import io.mangonet.mgo.model.move.kind.data.Package;

import java.io.IOException;

public class DataDeserializer extends JsonDeserializer<Data> {

    @Override
    public Data deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = p.getCodec().readTree(p);

        if (!node.has("dataType")) {
            throw new IllegalArgumentException("Missing dataType field in Data");
        }

        String dataType = node.get("dataType").asText();

        switch (dataType) {
            case "moveObject":
                return mapper.treeToValue(node, MoveObject.class);
            case "package":
                return mapper.treeToValue(node, Package.class);
            default:
                throw new IllegalArgumentException("Unknown Data type: " + dataType);
        }
    }
}
