package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.mangonet.mgo.model.object.kind.InputObjectKind;
import io.mangonet.mgo.model.object.kind.input.ImmOrOwnedMoveObject;
import io.mangonet.mgo.model.object.kind.input.MovePackage;
import io.mangonet.mgo.model.object.kind.input.SharedMoveObject;

import java.io.IOException;
import java.util.Iterator;

public class InputObjectKindDeserializer  extends JsonDeserializer<InputObjectKind> {

    @Override
    public InputObjectKind deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode node = mapper.readTree(p);

        // get all field names
        Iterator<String> fieldNames = node.fieldNames();

        if (fieldNames.hasNext()) {
            String typeField = fieldNames.next();

            switch (typeField) {
                case "MovePackage":
                    MovePackage movePackage = new MovePackage();
                    movePackage.setMovePackage(node.get(typeField).asText());
                    return movePackage;
                case "ImmOrOwnedMoveObject":
                    return mapper.treeToValue(node.get(typeField), ImmOrOwnedMoveObject.class);
                case "SharedMoveObject":
                    return mapper.treeToValue(node.get(typeField), SharedMoveObject.class);
                default:
                    throw ctxt.instantiationException(InputObjectKind.class,
                            "Unknown InputObjectKind type: " + typeField);
            }
        }

        throw ctxt.instantiationException(InputObjectKind.class,
                "Empty InputObjectKind object");
    }

}
