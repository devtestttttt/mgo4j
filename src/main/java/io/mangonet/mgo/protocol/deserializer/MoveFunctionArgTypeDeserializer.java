package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.mangonet.mgo.model.move.kind.MoveFunctionArgType;
import io.mangonet.mgo.model.move.kind.arg.ObjectValueKind;
import io.mangonet.mgo.model.move.kind.arg.PureArgType;

import java.io.IOException;

public class MoveFunctionArgTypeDeserializer extends JsonDeserializer<MoveFunctionArgType> {


    @Override
    public MoveFunctionArgType deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        // Process the string form of "Pure".
        if (node.isTextual()) {
            if ("Pure".equals(node.asText())) {
                PureArgType pure = new PureArgType();
                pure.setType("Pure");
                return pure;
            }
            throw ctxt.instantiationException(MoveFunctionArgType.class,
                    "Unknown string type: " + node.asText());
        }

        // Process the object form of {"Object": "ByValue"}.
        if (node.isObject()) {
            JsonNode objectNode = node.get("Object");
            if (objectNode != null && objectNode.isTextual()) {
                try {
                    ObjectValueKind kind = new ObjectValueKind();
                    kind.setObject(objectNode.asText());
                    return kind;
                } catch (IllegalArgumentException e) {
                    throw ctxt.instantiationException(MoveFunctionArgType.class,
                            "Invalid ObjectValueKind: " + objectNode.asText());
                }
            }
        }

        throw ctxt.instantiationException(MoveFunctionArgType.class,
                "Invalid MoveFunctionArgType format: " + node);
    }

}
