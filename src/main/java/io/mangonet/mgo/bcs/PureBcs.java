package io.mangonet.mgo.bcs;

import io.mangonet.mgo.bcs.types.arg.call.CallArg;
import io.mangonet.mgo.bcs.types.arg.call.CallArgPure;
import io.mangonet.mgo.bcs.types.transaction.Argument;
import io.mangonet.mgo.bcs.types.transaction.Command;
import io.mangonet.mgo.bcs.types.transaction.ProgrammableMoveCall;
import io.mangonet.mgo.bcs.types.transaction.ProgrammableTransaction;
import io.mangonet.mgo.client.QueryBuilder;
import io.mangonet.mgo.model.move.MgoMoveNormalizedFunction;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import io.mangonet.mgo.model.move.kind.type.PrimitiveType;
import io.mangonet.mgo.model.move.kind.type.VectorType;
import io.mangonet.mgo.protocol.MgoClient;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PureBcs {
    
    private static final Pattern VECTOR_PATTERN = Pattern.compile("^vector<(.+)>$");
    private static final Pattern OPTION_PATTERN = Pattern.compile("^option<(.+)>$");

    /**
     * Basic primitive types.
     */
    public enum BasePureType {
        U8, U16, U32, U64, U128, U256, BOOL, STRING, ADDRESS, VECTOR_U8
    }
    
    /**
     * Get the BCS serializer by type name.
     */
    public static BcsSerializer.BcsTypeSerializer<?> getSerializer(String typeName) {
        if (typeName == null || typeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Type name cannot be null or empty");
        }
        
        typeName = typeName.trim();
        
        // primitive types
        switch (typeName.toLowerCase()) {
            case "u8":
                return (serializer, value) -> serializer.writeUleb128(1).writeU8((Byte) value);
            case "u16":
                return (serializer, value) -> serializer.writeUleb128(2).writeU16((Short) value);
            case "u32":
                return (serializer, value) -> serializer.writeUleb128(4).writeU32((Integer) value);
            case "u64":
                return (serializer, value) -> serializer.writeUleb128(8).writeU64((Long) value);
            case "u128":
                return (serializer, value) -> serializer.writeUleb128(16).writeU128((BigInteger) value);
            case "u256":
                return (serializer, value) -> serializer.writeUleb128(32).writeU256((BigInteger) value);
            case "bool":
                return (serializer, value) -> serializer.writeUleb128(1).writeBool((Boolean) value);
            case "string":
                return (serializer, value) -> serializer.writeString((String) value);
            case "address":
                return (serializer, value) -> serializer.writeUleb128(32).writeAddress((String) value);
            case "vector_u8":
                return (serializer, value) -> serializer.writeVector((byte[]) value);
        }
        
        // vector type
        Matcher vectorMatch = VECTOR_PATTERN.matcher(typeName);
        if (vectorMatch.matches()) {
            String elementType = vectorMatch.group(1);
            BcsSerializer.BcsTypeSerializer<?> elementSerializer = getSerializer(elementType);
            return (serializer, value) -> {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) value;
                serializer.writeVector(list, (BcsSerializer.BcsTypeSerializer<Object>) elementSerializer);
            };
        }
        
        // option type
        Matcher optionMatch = OPTION_PATTERN.matcher(typeName);
        if (optionMatch.matches()) {
            String elementType = optionMatch.group(1);
            BcsSerializer.BcsTypeSerializer<?> elementSerializer = getSerializer(elementType);
            return (serializer, value) -> {
                serializer.writeOption(value, (BcsSerializer.BcsTypeSerializer<Object>) elementSerializer);
            };
        }
        
        throw new IllegalArgumentException("Invalid Pure type name: " + typeName);
    }
    
    /**
     * Validate if the primitive type name is valid.
     */
    public static boolean isValidPureTypeName(String typeName) {
        if (typeName == null || typeName.trim().isEmpty()) {
            return false;
        }
        
        typeName = typeName.trim();
        
        // Check primitive types.
        for (BasePureType type : BasePureType.values()) {
            if (typeName.equalsIgnoreCase(type.name())) {
                return true;
            }
        }
        
        // check vector type
        Matcher vectorMatcher = VECTOR_PATTERN.matcher(typeName);
        if (vectorMatcher.matches()) {
            String elementType = vectorMatcher.group(1);
            return isValidPureTypeName(elementType);
        }
        
        // check option type
        Matcher optionMatcher = OPTION_PATTERN.matcher(typeName);
        if (optionMatcher.matches()) {
            String elementType = optionMatcher.group(1);
            return isValidPureTypeName(elementType);
        }
        
        return false;
    }
    
    /**
     * Serialize primitive type value to Base64.
     */
    public static String serializeToBase64(String typeName, Object value) throws IOException {
        BcsSerializer.BcsTypeSerializer<?> serializer = getSerializer(typeName);
        BcsSerializer bcsSerializer = new BcsSerializer();
        ((BcsSerializer.BcsTypeSerializer<Object>) serializer).serialize(bcsSerializer, value);
        return bcsSerializer.toBase64();
    }
    
    /**
     * Deserialize primitive type value from Base64.
     */
    public static Object deserializeFromBase64(String typeName, String base64) throws IOException {
        byte[] data = Base64.decode(base64);
        BcsDeserializer deserializer = new BcsDeserializer(data);
        return deserializePureEncodedValue(deserializer, typeName);
    }

    /**
     * Deserialize primitive type value from bytes.
     */
    public static Object deserializeFromBytes(String typeName, byte[] data) throws IOException {
        BcsDeserializer deserializer = new BcsDeserializer(data);
        return deserializeValue(deserializer, typeName);
    }
    
    /**
     * Deserialize value
     */
    public static Object deserializeValue(BcsDeserializer deserializer, String typeName) throws IOException {
        switch (typeName.toLowerCase()) {
            case "u8":
                return deserializer.readU8();
            case "u16":
                return deserializer.readU16();
            case "u32":
                return deserializer.readU32();
            case "u64":
                return deserializer.readU64();
            case "u128":
                return deserializer.readU128();
            case "u256":
                return deserializer.readU256();
            case "bool":
                return deserializer.readBool();
            case "string":
                return deserializer.readString();
            case "address":
                return MgoBcs.ADDRESS_DESERIALIZER.deserialize(deserializer); // Process address as a string.
            case "vector_u8":
                return deserializer.readVector();
        }
        
        // vector type
        Matcher vectorMatch = VECTOR_PATTERN.matcher(typeName);
        if (vectorMatch.matches()) {
            String elementType = vectorMatch.group(1);
            return deserializer.readVector(d -> {
                try {
                    return deserializeValue(d, elementType);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to deserialize vector element", e);
                }
            });
        }
        
        // option type
        Matcher optionMatch = OPTION_PATTERN.matcher(typeName);
        if (optionMatch.matches()) {
            String elementType = optionMatch.group(1);
            return deserializer.readOption(d -> {
                try {
                    return deserializeValue(d, elementType);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to deserialize option element", e);
                }
            });
        }
        
        throw new IllegalArgumentException("Invalid Pure type name: " + typeName);
    }

    /**
     * Deserialize Pure-encoded value (the format produced by getSerializer + serializeToBase64).
     */
    private static Object deserializePureEncodedValue(BcsDeserializer deserializer, String typeName) throws IOException {
        String normalizedType = typeName.toLowerCase();

        switch (normalizedType) {
            case "u8":
                readAndCheckLengthPrefix(deserializer, 1, typeName);
                return deserializer.readU8();
            case "u16":
                readAndCheckLengthPrefix(deserializer, 2, typeName);
                return deserializer.readU16();
            case "u32":
                readAndCheckLengthPrefix(deserializer, 4, typeName);
                return deserializer.readU32();
            case "u64":
                readAndCheckLengthPrefix(deserializer, 8, typeName);
                return deserializer.readU64();
            case "u128":
                readAndCheckLengthPrefix(deserializer, 16, typeName);
                return deserializer.readU128();
            case "u256":
                readAndCheckLengthPrefix(deserializer, 32, typeName);
                return deserializer.readU256();
            case "bool":
                readAndCheckLengthPrefix(deserializer, 1, typeName);
                return deserializer.readBool();
            case "string":
                return deserializer.readString();
            case "address":
                readAndCheckLengthPrefix(deserializer, 32, typeName);
                return MgoBcs.ADDRESS_DESERIALIZER.deserialize(deserializer);
            case "vector_u8":
                int len = deserializer.readUleb128Pure();
                return deserializer.readFixedBytes(len);
        }

        Matcher vectorMatch = VECTOR_PATTERN.matcher(typeName);
        if (vectorMatch.matches()) {
            String elementType = vectorMatch.group(1);
            return deserializer.readVector(d -> {
                try {
                    return deserializePureEncodedValue(d, elementType);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to deserialize vector element", e);
                }
            });
        }

        Matcher optionMatch = OPTION_PATTERN.matcher(typeName);
        if (optionMatch.matches()) {
            String elementType = optionMatch.group(1);
            return deserializer.readOption(d -> {
                try {
                    return deserializePureEncodedValue(d, elementType);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to deserialize option element", e);
                }
            });
        }

        throw new IllegalArgumentException("Invalid Pure type name: " + typeName);
    }

    private static void readAndCheckLengthPrefix(BcsDeserializer deserializer, int expectedLength, String typeName) throws IOException {
        int actualLength = deserializer.readUleb128();
        if (actualLength != expectedLength) {
            throw new IOException("Invalid length prefix for " + typeName + ": expected " + expectedLength + ", but got " + actualLength);
        }
    }

    /**
     * Parse the pure bytes of each MoveCall into specific values using on-chain function signatures
     * @param programmableTx
     * @param mgoClient
     */
    public static void resolvePureArgsTypes(ProgrammableTransaction programmableTx, MgoClient mgoClient) {
        List<Command> commands = programmableTx.getCommands();
        LinkedHashMap<CallArg, Integer> inputs = programmableTx.getInputs();
        if (commands != null && !commands.isEmpty()) {
            for (Command command : commands) {
                if (command instanceof Command.MoveCall moveCall) {
                    ProgrammableMoveCall programmableMoveCall = moveCall.getMoveCall();
                    MgoMoveNormalizedFunction moveFunction = QueryBuilder.getMoveFunction(mgoClient, programmableMoveCall.getPackageId(), programmableMoveCall.getModule(), programmableMoveCall.getFunction());
                    List<MgoMoveNormalizedType> parameters = moveFunction.getParameters();
                    List<Argument> arguments = programmableMoveCall.getArguments();
                    if (arguments != null && !arguments.isEmpty() && parameters != null && !parameters.isEmpty()) {
                        int size = parameters.size();
                        for (int i = 0; i < size; i++) {
                            MgoMoveNormalizedType mgoMoveNormalizedType = parameters.get(i);
                            String type = null;
                            if (mgoMoveNormalizedType instanceof PrimitiveType primitiveType) {
                                type = primitiveType.getType();
                            } else if (mgoMoveNormalizedType instanceof VectorType vectorType) {
                                type = "vector_" + vectorType.getVector().getType();
                            }
                            if (type != null) {
                                Argument.Input input = (Argument.Input) arguments.get(i);
                                int finalIndex = input.getIndex();
                                CallArg callArg = inputs.entrySet().stream()
                                        .filter(e -> e.getValue() == finalIndex)
                                        .map(Map.Entry::getKey)
                                        .findFirst()
                                        .orElse(null);
                                if (callArg != null) {
                                    try {
                                    CallArgPure pure = (CallArgPure) callArg;
                                    Object arg = PureBcs.deserializeFromBytes(type, pure.getRawBytes());
                                    pure.setArg(arg);
                                    pure.setBasePureType(BasePureType.valueOf(type.toUpperCase()));
                                    } catch (IOException e) {
                                        throw new IllegalArgumentException("Failed to deserialize MoveCall Pure type " + type, e);
                                    }
                                }
                            }
                        }
                    }
                } else if (command instanceof Command.SplitCoins splitCoins) {
                    List<Argument> amounts = splitCoins.getAmounts();
                    BasePureType u64 = BasePureType.U64;
                    if (amounts != null && !amounts.isEmpty()) {
                        for (Argument amount : amounts) {
                            String type = u64.name();
                            try {
                                Argument.Input input = (Argument.Input) amount;
                                int finalIndex = input.getIndex();
                                CallArgPure pure = (CallArgPure) inputs.entrySet().stream()
                                        .filter(e -> e.getValue() == finalIndex)
                                        .map(Map.Entry::getKey)
                                        .findFirst()
                                        .orElseThrow(() -> new IllegalStateException(
                                                "CallArg index " + finalIndex + " not found in SplitCoins of ProgrammableTransaction"
                                        ));
                                Object arg = PureBcs.deserializeFromBytes(type, pure.getRawBytes());
                                pure.setArg(arg);
                                pure.setBasePureType(u64);
                            } catch (IOException e) {
                                throw new IllegalArgumentException("Failed to deserialize SplitCoins Pure type " + type, e);
                            }
                        }
                    }
                }
            }
        }
    }

} 
