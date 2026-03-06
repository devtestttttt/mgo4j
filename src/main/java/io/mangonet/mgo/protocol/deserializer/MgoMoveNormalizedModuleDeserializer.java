package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.mangonet.mgo.model.move.*;
import io.mangonet.mgo.model.move.*;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MgoMoveNormalizedModuleDeserializer extends JsonDeserializer<MgoMoveNormalizedModule> {

    @Override
    public MgoMoveNormalizedModule deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode rootNode = mapper.readTree(p);
        MgoMoveNormalizedModule module = new MgoMoveNormalizedModule();

        // 1. Parse required fields
        module.setFileFormatVersion(getLongField(rootNode, "fileFormatVersion", ctxt));
        module.setAddress(getTextField(rootNode, "address", ctxt));
        module.setName(getTextField(rootNode, "name", ctxt));

        // 2. Parse optional fields
        module.setFriends(parseFriends(rootNode.get("friends"), ctxt));
        module.setStructs(parseStructs(rootNode.get("structs"), mapper, ctxt));
        module.setEnums(parseEnums(rootNode.get("enums"), mapper, ctxt));
        module.setExposedFunctions(parseFunctions(rootNode.get("exposedFunctions"), mapper, ctxt));

        return module;
    }

    // ================ Basic field parsing ================
    private long getLongField(JsonNode node, String field, DeserializationContext ctxt)
            throws IOException {
        if (!node.has(field)) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedModule.class,
                    "Missing required field: " + field
            );
        }
        return node.get(field).asLong();
    }

    private String getTextField(JsonNode node, String field, DeserializationContext ctxt)
            throws IOException {
        if (!node.has(field)) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedModule.class,
                    "Missing required field: " + field
            );
        }
        return node.get(field).asText();
    }

    // ================ Nested type parsing ================
    private List<MgoMoveModuleId> parseFriends(JsonNode node, DeserializationContext ctxt)
            throws IOException {
        List<MgoMoveModuleId> friends = new ArrayList<>();
        if (node == null || node.isNull()) {
            return friends;
        }

        if (!node.isArray()) {
            throw ctxt.instantiationException(
                    MgoMoveModuleId.class,
                    "friends must be an array, got: " + node.getNodeType()
            );
        }

        for (JsonNode friendNode : node) {
            friends.add(parseModuleId(friendNode, ctxt));
        }
        return friends;
    }

    private MgoMoveModuleId parseModuleId(JsonNode node, DeserializationContext ctxt)
            throws IOException {
        if (!node.isObject()) {
            throw ctxt.instantiationException(
                    MgoMoveModuleId.class,
                    "ModuleId must be an object, got: " + node.getNodeType()
            );
        }

        MgoMoveModuleId moduleId = new MgoMoveModuleId();
        moduleId.setAddress(getTextField(node, "address", ctxt));
        moduleId.setName(getTextField(node, "name", ctxt));
        return moduleId;
    }

    private Map<String, MgoMoveNormalizedStruct> parseStructs(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        Map<String, MgoMoveNormalizedStruct> structs = new HashMap<>();
        if (node == null || node.isNull()) {
            return structs;
        }

        if (!node.isObject()) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedStruct.class,
                    "structs must be an object, got: " + node.getNodeType()
            );
        }

        node.fields().forEachRemaining(entry -> {
            try {
                structs.put(entry.getKey(), parseStruct(entry.getValue(), mapper, ctxt));
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse struct: " + entry.getKey(), e);
            }
        });
        return structs;
    }

    private MgoMoveNormalizedStruct parseStruct(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        MgoMoveNormalizedStruct struct = new MgoMoveNormalizedStruct();

        // Parse required fields
        struct.setAbilities(parseAbilitySet(node.get("abilities"), mapper, ctxt));
        struct.setFields(parseFields(node.get("fields"), mapper, ctxt));
        struct.setTypeParameters(parseTypeParameters(node.get("typeParameters"), mapper, ctxt));

        return struct;
    }

    private List<MgoMoveNormalizedField> parseFields(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        List<MgoMoveNormalizedField> fields = new ArrayList<>();
        if (!node.isArray()) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedField.class,
                    "fields must be an array, got: " + node.getNodeType()
            );
        }

        for (JsonNode fieldNode : node) {
            MgoMoveNormalizedField field = new MgoMoveNormalizedField();
            field.setName(getTextField(fieldNode, "name", ctxt));
            field.setType(mapper.readValue(fieldNode.get("type").toString(), MgoMoveNormalizedType.class));
            fields.add(field);
        }
        return fields;
    }

    private List<MgoMoveStructTypeParameter> parseTypeParameters(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        List<MgoMoveStructTypeParameter> params = new ArrayList<>();
        if (!node.isArray()) {
            throw ctxt.instantiationException(
                    MgoMoveStructTypeParameter.class,
                    "typeParameters must be an array, got: " + node.getNodeType()
            );
        }

        for (JsonNode paramNode : node) {
            MgoMoveStructTypeParameter param = new MgoMoveStructTypeParameter();
            param.setConstraints(parseAbilitySet(paramNode.get("constraints"), mapper, ctxt));
            param.setIsPhantom(paramNode.get("isPhantom").asBoolean());
            params.add(param);
        }
        return params;
    }

    private Map<String, MgoMoveNormalizedEnum> parseEnums(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        Map<String, MgoMoveNormalizedEnum> enums = new HashMap<>();
        if (node == null || node.isNull()) {
            return enums;
        }

        if (!node.isObject()) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedEnum.class,
                    "enums must be an object, got: " + node.getNodeType()
            );
        }

        node.fields().forEachRemaining(entry -> {
            try {
                enums.put(entry.getKey(), parseEnum(entry.getValue(), mapper, ctxt));
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse enum: " + entry.getKey(), e);
            }
        });
        return enums;
    }

    private MgoMoveNormalizedEnum parseEnum(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        MgoMoveNormalizedEnum enumType = new MgoMoveNormalizedEnum();

        // Parse required fields
        enumType.setAbilities(parseAbilitySet(node.get("abilities"), mapper, ctxt));
        enumType.setTypeParameters(parseTypeParameters(node.get("typeParameters"), mapper, ctxt));

        // Parse optional fields
        if (node.has("variantDeclarationOrder")) {
            enumType.setVariantDeclarationOrder(parseStringList(node.get("variantDeclarationOrder")));
        }

        // parsevariants
        JsonNode variantsNode = node.get("variants");
        if (!variantsNode.isObject()) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedEnum.class,
                    "variants must be an object, got: " + variantsNode.getNodeType()
            );
        }

        Map<String, List<MgoMoveNormalizedField>> variants = new HashMap<>();
        variantsNode.fields().forEachRemaining(entry -> {
            try {
                variants.put(entry.getKey(), parseFields(entry.getValue(), mapper, ctxt));
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse variant: " + entry.getKey(), e);
            }
        });
        enumType.setVariants(variants);

        return enumType;
    }

    private Map<String, MgoMoveNormalizedFunction> parseFunctions(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        Map<String, MgoMoveNormalizedFunction> functions = new HashMap<>();
        if (node == null || node.isNull()) {
            return functions;
        }

        if (!node.isObject()) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedFunction.class,
                    "exposedFunctions must be an object, got: " + node.getNodeType()
            );
        }

        node.fields().forEachRemaining(entry -> {
            try {
                functions.put(entry.getKey(), parseFunction(entry.getValue(), mapper, ctxt));
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse function: " + entry.getKey(), e);
            }
        });
        return functions;
    }

    private MgoMoveNormalizedFunction parseFunction(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        MgoMoveNormalizedFunction function = new MgoMoveNormalizedFunction();

        // Parse required fields
        function.setIsEntry(node.get("isEntry").asBoolean());
        function.setParameters(parseTypeList(node.get("parameters"), mapper, ctxt));
        function.setReturnTypes(parseTypeList(node.get("return"), mapper, ctxt));
        function.setTypeParameters(parseAbilitySetList(node.get("typeParameters"), mapper, ctxt));
        function.setVisibility(MgoMoveVisibility.findByValue(node.get("visibility").asText()).getValue());

        return function;
    }

    // ================ utility method ================

    private MgoMoveAbilitySet parseAbilitySet(JsonNode node, ObjectMapper mapper , DeserializationContext ctxt)
            throws IOException {
        if (node == null || node.isNull()) {
            throw ctxt.instantiationException(
                    MgoMoveAbilitySet.class,
                    "abilities cannot be null"
            );
        }
        return mapper.readValue(node.toString(), MgoMoveAbilitySet.class);
    }

    private List<MgoMoveAbilitySet> parseAbilitySetList(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        List<MgoMoveAbilitySet> list = new ArrayList<>();
        if (!node.isArray()) {
            throw ctxt.instantiationException(
                    MgoMoveAbilitySet.class,
                    "typeParameters must be an array, got: " + node.getNodeType()
            );
        }

        for (JsonNode item : node) {
            list.add(parseAbilitySet(item, mapper, ctxt));
        }
        return list;
    }

    private List<MgoMoveNormalizedType> parseTypeList(JsonNode node, ObjectMapper mapper, DeserializationContext ctxt)
            throws IOException {
        List<MgoMoveNormalizedType> types = new ArrayList<>();
        if (!node.isArray()) {
            throw ctxt.instantiationException(
                    MgoMoveNormalizedType.class,
                    "parameters must be an array, got: " + node.getNodeType()
            );
        }

        for (JsonNode typeNode : node) {
            types.add(mapper.readValue(typeNode.toString(), MgoMoveNormalizedType.class));
        }
        return types;
    }

    private List<String> parseStringList(JsonNode node) {
        List<String> list = new ArrayList<>();
        if (node != null && node.isArray()) {
            for (JsonNode item : node) {
                list.add(item.asText());
            }
        }
        return list;
    }
}
