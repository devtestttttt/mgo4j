package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import io.mangonet.mgo.model.object.kind.change.*;
import io.mangonet.mgo.model.object.kind.change.*;

import java.io.IOException;

public class ObjectChangeDeserializer extends JsonDeserializer<ObjectChange> {

    @Override
    public ObjectChange deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode node = mapper.readTree(p);

        if (!node.has("type")) {
            throw ctxt.instantiationException(ObjectChange.class,
                    "Missing required field 'type' in MgoObjectChange");
        }

        String type = node.get("type").asText();

        try {
            switch (type) {
                case "published":
                    return mapper.convertValue(node, Published.class);
                case "transferred":
                    return mapper.convertValue(node, Transferred.class);
                case "mutated":
                    return mapper.convertValue(node, Mutated.class);
                case "deleted":
                    return mapper.convertValue(node, Deleted.class);
                case "wrapped":
                    return mapper.convertValue(node, Wrapped.class);
                case "created":
                    return mapper.convertValue(node, Created.class);
                default:
                    throw ctxt.instantiationException(ObjectChange.class,
                            "Unknown MgoObjectChange type: " + type);
            }
        } catch (Exception e) {
            throw ctxt.instantiationException(ObjectChange.class,
                    "Failed to deserialize MgoObjectChange of type " + type);
        }
    }
}
