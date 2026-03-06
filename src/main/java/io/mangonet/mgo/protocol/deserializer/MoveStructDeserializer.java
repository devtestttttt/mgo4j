package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.move.kind.MoveStruct;
import io.mangonet.mgo.model.move.kind.MoveValue;
import io.mangonet.mgo.model.move.kind.struct.MoveStructArray;
import io.mangonet.mgo.model.move.kind.struct.MoveStructMap;
import io.mangonet.mgo.model.move.kind.struct.MoveStructObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MoveStructDeserializer extends JsonDeserializer<MoveStruct> {

    @Override
    public MoveStruct deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        if (node.isArray()) {
            MoveStructArray array = new MoveStructArray();
            List<MoveValue> values = mapper.convertValue(node, new TypeReference<List<MoveValue>>() {});
            array.setValues(values);
            return array;
        } else if (node.has("type") && node.has("fields")) {
            MoveStructObject obj = new MoveStructObject();
            obj.setType(node.get("type").asText());
            obj.setFields(mapper.convertValue(node.get("fields"), new TypeReference<Map<String, MoveValue>>() {}));
            return obj;
        } else if (node.isObject()) {
            MoveStructMap map = new MoveStructMap();
            map.setValues(mapper.convertValue(node, new TypeReference<Map<String, MoveValue>>() {}));
            return map;
        } else {
            throw new IllegalArgumentException("Unrecognized MoveStruct format");
        }
    }
}