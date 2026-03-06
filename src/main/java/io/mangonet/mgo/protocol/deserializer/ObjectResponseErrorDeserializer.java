package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.object.kind.ObjectResponseError;
import io.mangonet.mgo.model.object.kind.error.*;
import io.mangonet.mgo.model.object.kind.error.*;

import java.io.IOException;

public class ObjectResponseErrorDeserializer extends JsonDeserializer<ObjectResponseError> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ObjectResponseError deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (!node.has("code")) {
            throw new IllegalArgumentException("Missing code field in ObjectResponseError");
        }

        String code = node.get("code").asText();

        switch (code) {
            case "notExists":
                return mapper.treeToValue(node, NotExists.class);
            case "dynamicFieldNotFound":
                return mapper.treeToValue(node, DynamicFieldNotFound.class);
            case "deleted":
                return mapper.treeToValue(node, Deleted.class);
            case "unknown":
                return mapper.treeToValue(node, Unknown.class);
            case "displayError":
                return mapper.treeToValue(node, Display.class);
            default:
                throw new IllegalArgumentException("Unknown error code: " + code);
        }
    }
}
