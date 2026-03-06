package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.transaction.kind.ExecutionStatus;
import io.mangonet.mgo.model.transaction.kind.status.Failure;
import io.mangonet.mgo.model.transaction.kind.status.Success;

import java.io.IOException;

public class ExecutionStatusDeserializer extends JsonDeserializer<ExecutionStatus> {

    @Override
    public ExecutionStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        if (!node.has("status")) {
            throw ctxt.instantiationException(ExecutionStatus.class,
                    "Missing required field 'status' in ExecutionStatus");
        }

        String status = node.get("status").asText();

        try {
            switch (status) {
                case "success":
                    return mapper.convertValue(node, Success.class);
                case "failure":
                    return mapper.convertValue(node, Failure.class);
                default:
                    throw ctxt.instantiationException(ExecutionStatus.class,
                            "Unknown ExecutionStatus: " + status);
            }
        } catch (Exception e) {
            throw ctxt.instantiationException(ExecutionStatus.class,
                    "Failed to deserialize ExecutionStatus");
        }
    }
}
