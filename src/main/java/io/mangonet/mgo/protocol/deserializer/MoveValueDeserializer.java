package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.move.kind.MoveValue;
import io.mangonet.mgo.model.move.kind.struct.MoveStructObject;
import io.mangonet.mgo.model.move.kind.value.MoveVariant;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MoveValueDeserializer extends JsonDeserializer<MoveValue> {

    @Override
    public MoveValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        MoveValue mv = new MoveValue();

        if (node.isInt()) {
            mv.setValueType(MoveValue.MoveValueType.UINT32);
            mv.setValue(node.intValue());
        } else if (node.isBoolean()) {
            mv.setValueType(MoveValue.MoveValueType.BOOLEAN);
            mv.setValue(node.booleanValue());
        } else if (node.isTextual()) {
            mv.setValueType(MoveValue.MoveValueType.STRING);
            mv.setValue(node.textValue());
        } else if (node.isNull()) {
            mv.setValueType(MoveValue.MoveValueType.NULL);
            mv.setValue(null);
        } else if (node.isArray()) {
            mv.setValueType(MoveValue.MoveValueType.ARRAY);
            mv.setValue(mapper.convertValue(node, new TypeReference<List<MoveValue>>() {}));
        } else if (node.has("id")) {
            mv.setValueType(MoveValue.MoveValueType.ID_OBJECT);
            mv.setValue(mapper.convertValue(node, Map.class));
        } else if (node.has("fields") && node.has("type") && node.has("variant")) {
            mv.setValueType(MoveValue.MoveValueType.VARIANT);
            mv.setValue(mapper.convertValue(node, MoveVariant.class));
        } else if (node.has("type") && node.has("fields")) {
            mv.setValueType(MoveValue.MoveValueType.STRUCT_OBJECT);
            mv.setValue(mapper.convertValue(node, MoveStructObject.class));
        } else {
            throw new IllegalArgumentException("Unknown MoveValue structure: " + node.toPrettyString());
        }

        return mv;
    }
}
