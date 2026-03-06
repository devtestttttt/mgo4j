package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import io.mangonet.mgo.model.move.kind.type.*;
import io.mangonet.mgo.model.move.kind.type.*;
import io.mangonet.mgo.model.move.kind.type.enums.PrimitiveEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MgoMoveNormalizedTypeDeserializer extends JsonDeserializer<MgoMoveNormalizedType> {

    @Override
    public MgoMoveNormalizedType deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        // 1. Handle basic types (string form)
        if (node.isTextual()) {
            return handlePrimitiveType(node, ctxt);
        }

        // 2. Handle object form
        if (node.isObject()) {
            // Check all possible type fields
            if (node.has("Struct")) {
                return handleStructType(node, mapper, ctxt);
            } else if (node.has("Vector")) {
                return handleVectorType(node, mapper, ctxt);
            } else if (node.has("TypeParameter")) {
                return handleTypeParameterType(node, ctxt);
            } else if (node.has("Reference")) {
                return handleReferenceType(node, mapper, ctxt);
            } else if (node.has("MutableReference")) {
                return handleMutableReferenceType(node, mapper, ctxt);
            }
        }

        throw ctxt.instantiationException(
                MgoMoveNormalizedType.class,
                "Invalid MgoMoveNormalizedType format. Expected one of: " +
                        "String enum, Struct, Vector, TypeParameter, Reference or MutableReference. " +
                        "Got: " + node.getNodeType()
        );
    }

    private PrimitiveType handlePrimitiveType(JsonNode node, DeserializationContext ctxt)
            throws IOException {
        String typeStr = node.asText();
        if (PrimitiveEnum.find(typeStr) == null) {
            throw ctxt.instantiationException(
                    PrimitiveType.class,
                    "Invalid primitive type: " + typeStr +
                            ". Valid values are: " + Arrays.toString(PrimitiveEnum.values())
            );
        }
        return new PrimitiveType(typeStr);
    }

    private StructType handleStructType(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        JsonNode structNode = node.get("Struct");

        // Verify required fields
        validateRequiredFields(structNode, ctxt, "Struct",
                "address", "module", "name", "typeArguments");

        try {
            return new StructType(
                    structNode.get("address").asText(),
                    structNode.get("module").asText(),
                    structNode.get("name").asText(),
                    parseTypeArguments(structNode.get("typeArguments"), mapper, ctxt)
            );
        } catch (Exception e) {
            throw ctxt.instantiationException(
                    StructType.class,
                    "Failed to parse Struct: " + e.getMessage()
            );
        }
    }

    private VectorType handleVectorType(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        try {
            MgoMoveNormalizedType elementType = mapper.readValue(
                    node.get("Vector").toString(),
                    MgoMoveNormalizedType.class
            );
            VectorType vectorType = new VectorType();
            vectorType.setVector(elementType);
            return vectorType;
        } catch (Exception e) {
            throw ctxt.instantiationException(
                    VectorType.class,
                    "Failed to parse Vector: " + e.getMessage()
            );
        }
    }

    private TypeParameterType handleTypeParameterType(JsonNode node, DeserializationContext ctxt)
            throws IOException {
        int index = node.get("TypeParameter").asInt();
        if (index < 0) {
            throw ctxt.instantiationException(
                    TypeParameterType.class,
                    "TypeParameter index must be >= 0, got: " + index
            );
        }
        TypeParameterType typeParam = new TypeParameterType();
        typeParam.setTypeParameter(index); // Note: adjust according to actual class definition
        return typeParam;
    }

    private ReferenceType handleReferenceType(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        try {
            MgoMoveNormalizedType referencedType = mapper.readValue(
                    node.get("Reference").toString(),
                    MgoMoveNormalizedType.class
            );
            ReferenceType refType = new ReferenceType();
            refType.setReference(referencedType);
            return refType;
        } catch (Exception e) {
            throw ctxt.instantiationException(
                    ReferenceType.class,
                    "Failed to parse Reference: " + e.getMessage()
            );
        }
    }

    private MutableReferenceType handleMutableReferenceType(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        try {
            MgoMoveNormalizedType referencedType = mapper.readValue(
                    node.get("MutableReference").toString(),
                    MgoMoveNormalizedType.class
            );
            MutableReferenceType mutRefType = new MutableReferenceType();
            mutRefType.setMutableReference(referencedType);
            return mutRefType;
        } catch (Exception e) {
            throw ctxt.instantiationException(
                    MutableReferenceType.class,
                    "Failed to parse MutableReference: " + e.getMessage()
            );
        }
    }

    private List<MgoMoveNormalizedType> parseTypeArguments(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        if (!node.isArray()) {
            throw ctxt.instantiationException(
                    StructType.class,
                    "typeArguments must be an array, got: " + node.getNodeType()
            );
        }

        List<MgoMoveNormalizedType> typeArgs = new ArrayList<>();
        for (JsonNode argNode : node) {
            typeArgs.add(mapper.readValue(argNode.toString(), MgoMoveNormalizedType.class));
        }
        return typeArgs;
    }

    private void validateRequiredFields(JsonNode node, DeserializationContext ctxt,
                                        String parentType, String... fields)
            throws IOException {
        for (String field : fields) {
            if (!node.has(field)) {
                throw ctxt.instantiationException(
                        MgoMoveNormalizedType.class,
                        "Missing required field '" + field + "' in " + parentType
                );
            }
        }
    }
}
