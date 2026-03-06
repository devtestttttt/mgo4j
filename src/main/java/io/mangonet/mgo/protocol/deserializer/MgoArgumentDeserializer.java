package io.mangonet.mgo.protocol.deserializer;

import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import io.mangonet.mgo.model.mgo.kind.arg.GasCoin;
import io.mangonet.mgo.model.mgo.kind.arg.Input;
import io.mangonet.mgo.model.mgo.kind.arg.NestedResult;
import io.mangonet.mgo.model.mgo.kind.arg.Result;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MgoArgumentDeserializer extends JsonDeserializer<MgoArgument> {

    @Override
    public MgoArgument deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        // Process the string form of "GasCoin".
        if (node.isTextual() && "GasCoin".equals(node.asText())) {
            return new GasCoin();
        }

        // Process parameters in object form.
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            if (fieldNames.hasNext()) {
                String type = fieldNames.next();

                switch (type) {
                    case "Input":
                        Input input = new Input();
                        input.setIndex(node.get("Input").asInt());
                        return input;
                    case "Result":
                        Result result = new Result();
                        result.setIndex(node.get("Result").asInt());
                        return result;
                    case "NestedResult":
                        NestedResult nestedResult = new NestedResult();
                        ArrayNode indices = (ArrayNode) node.get("NestedResult");
                        List<Integer> indexList = new ArrayList<>();
                        indices.forEach(n -> indexList.add(n.asInt()));
                        nestedResult.setIndices(indexList);
                        return nestedResult;
                    default:
                        throw ctxt.instantiationException(MgoArgument.class,
                                "Unknown MgoArgument type: " + type);
                }
            }
        }

        throw ctxt.instantiationException(MgoArgument.class,
                "Cannot deserialize MgoArgument from: " + node);
    }
}
