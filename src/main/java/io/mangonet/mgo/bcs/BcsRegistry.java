package io.mangonet.mgo.bcs;

import io.mangonet.mgo.bcs.types.arg.call.CallArg;
import io.mangonet.mgo.bcs.types.arg.object.ObjectArg;
import io.mangonet.mgo.bcs.types.arg.object.SharedObjectRef;
import io.mangonet.mgo.bcs.types.auth.PasskeyAuthenticator;
import io.mangonet.mgo.bcs.types.effects.GasCostSummary;
import io.mangonet.mgo.bcs.types.effects.TransactionEffects;
import io.mangonet.mgo.bcs.types.effects.TransactionEffectsV1;
import io.mangonet.mgo.bcs.types.gas.GasData;
import io.mangonet.mgo.bcs.types.gas.MgoObjectRef;
import io.mangonet.mgo.bcs.types.intent.*;
import io.mangonet.mgo.bcs.types.intent.*;
import io.mangonet.mgo.bcs.types.owner.Owner;
import io.mangonet.mgo.bcs.types.signature.*;
import io.mangonet.mgo.bcs.types.signature.*;
import io.mangonet.mgo.bcs.types.tag.TypeTag;
import io.mangonet.mgo.bcs.types.tag.TypeTagStructTag;
import io.mangonet.mgo.bcs.types.transaction.*;
import io.mangonet.mgo.bcs.types.transaction.*;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class BcsRegistry {
    
    // Cache all serializers
    private static final Map<Class<?>, BcsSerializer.BcsTypeSerializer<?>> SERIALIZER_REGISTRY = new ConcurrentHashMap<>();
    
    // Primitive type serializers.
    public static final BcsSerializer.BcsTypeSerializer<Byte> U8_SERIALIZER = (serializer, value) -> serializer.writeU8(value);
    public static final BcsSerializer.BcsTypeSerializer<Short> U16_SERIALIZER = (serializer, value) -> serializer.writeU16(value);
    public static final BcsSerializer.BcsTypeSerializer<Integer> U32_SERIALIZER = (serializer, value) -> serializer.writeU32(value);
    public static final BcsSerializer.BcsTypeSerializer<Long> U64_SERIALIZER = (serializer, value) -> serializer.writeU64(value);
    public static final BcsSerializer.BcsTypeSerializer<Boolean> BOOL_SERIALIZER = (serializer, value) -> serializer.writeBool(value);
    public static final BcsSerializer.BcsTypeSerializer<String> STRING_SERIALIZER = (serializer, value) -> serializer.writeString(value);
    public static final BcsSerializer.BcsTypeSerializer<byte[]> BYTE_ARRAY_SERIALIZER = (serializer, value) -> serializer.writeBytes(value);

    // Address and object related.
    public static final BcsSerializer.BcsTypeSerializer<String> ADDRESS_SERIALIZER = MgoBcs.ADDRESS_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<MgoObjectRef> MGO_OBJECT_REF_SERIALIZER = MgoBcs.MGO_OBJECT_REF_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<SharedObjectRef> SHARED_OBJECT_REF_SERIALIZER = MgoBcs.SHARED_OBJECT_REF_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<ObjectArg> OBJECT_ARG_SERIALIZER = MgoBcs.OBJECT_ARG_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<CallArg> CALL_ARG_SERIALIZER = MgoBcs.CALL_ARG_SERIALIZER;
    
    // Type tag related.
    public static final BcsSerializer.BcsTypeSerializer<TypeTag> TYPE_TAG_SERIALIZER = MgoBcs.TYPE_TAG_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<TypeTagStructTag> STRUCT_TAG_SERIALIZER = MgoBcs.STRUCT_TAG_SERIALIZER;
    
    // Transaction related.
    public static final BcsSerializer.BcsTypeSerializer<Argument> ARGUMENT_SERIALIZER = MgoBcs.ARGUMENT_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<ProgrammableMoveCall> PROGRAMMABLE_MOVE_CALL_SERIALIZER = MgoBcs.PROGRAMMABLE_MOVE_CALL_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<Command> COMMAND_SERIALIZER = MgoBcs.COMMAND_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<ProgrammableTransaction> PROGRAMMABLE_TRANSACTION_SERIALIZER = MgoBcs.PROGRAMMABLE_TRANSACTION_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<TransactionKind> TRANSACTION_KIND_SERIALIZER = MgoBcs.TRANSACTION_KIND_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<TransactionExpiration> TRANSACTION_EXPIRATION_SERIALIZER = MgoBcs.TRANSACTION_EXPIRATION_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<TransactionDataV1> TRANSACTION_DATA_V1_SERIALIZER = MgoBcs.TRANSACTION_DATA_V1_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<TransactionData> TRANSACTION_DATA_SERIALIZER = MgoBcs.TRANSACTION_DATA_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<GasData> GAS_DATA_SERIALIZER = MgoBcs.GAS_DATA_SERIALIZER;
    
    // Intent related.
    public static final BcsSerializer.BcsTypeSerializer<IntentScope> INTENT_SCOPE_SERIALIZER = MgoBcs.INTENT_SCOPE_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<IntentVersion> INTENT_VERSION_SERIALIZER = MgoBcs.INTENT_VERSION_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<AppId> APP_ID_SERIALIZER = MgoBcs.APP_ID_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<Intent> INTENT_SERIALIZER = MgoBcs.INTENT_SERIALIZER;

    public static final BcsSerializer.BcsTypeSerializer<IntentMessage<TransactionData>> INTENT_MESSAGE_SERIALIZER = MgoBcs.INTENT_MESSAGE_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<IntentMessage<String>> INTENT_MESSAGE_STR_SERIALIZER = MgoBcs.INTENT_MESSAGE_STR_SERIALIZER;

    // sign related.
    public static final BcsSerializer.BcsTypeSerializer<CompressedSignature> COMPRESSED_SIGNATURE_SERIALIZER = MgoBcs.COMPRESSED_SIGNATURE_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<PublicKey> PUBLIC_KEY_SERIALIZER = MgoBcs.PUBLIC_KEY_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<MultiSigPkMap> MULTI_SIG_PK_MAP_SERIALIZER = MgoBcs.MULTI_SIG_PK_MAP_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<MultiSigPublicKey> MULTI_SIG_PUBLIC_KEY_SERIALIZER = MgoBcs.MULTI_SIG_PUBLIC_KEY_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<MultiSig> MULTI_SIG_SERIALIZER = MgoBcs.MULTI_SIG_SERIALIZER;
    public static final BcsSerializer.BcsTypeSerializer<SenderSignedTransaction> SENDER_SIGNED_TRANSACTION_SERIALIZER = MgoBcs.SENDER_SIGNED_TRANSACTION_SERIALIZER;
    
    // Authenticator related.
    public static final BcsSerializer.BcsTypeSerializer<PasskeyAuthenticator> PASSKEY_AUTHENTICATOR_SERIALIZER = MgoBcs.PASSKEY_AUTHENTICATOR_SERIALIZER;
    
    // owner related.
    public static final BcsSerializer.BcsTypeSerializer<Owner> OWNER_SERIALIZER = MgoBcs.OWNER_SERIALIZER;
    
    /**
     * Get the Mgo BCS instance, corresponding to TypeScript's `mgoBcs`.
     */
    public static MgoBcs getBcs() {
        return new MgoBcs() {};
    }
    
    /**
     * get serializer
     */
    @SuppressWarnings("unchecked")
    public static <T> BcsSerializer.BcsTypeSerializer<T> getSerializer(Class<T> clazz) {
        return (BcsSerializer.BcsTypeSerializer<T>) SERIALIZER_REGISTRY.get(clazz);
    }
    
    /**
     * register serializer
     */
    public static <T> void registerSerializer(Class<T> clazz, BcsSerializer.BcsTypeSerializer<T> serializer) {
        SERIALIZER_REGISTRY.put(clazz, serializer);
    }

    /**
     * Serialize object to Base64 string.
     */
    public static <T> String serializeToBase64(T obj, BcsSerializer.BcsTypeSerializer<T> serializer) throws IOException {
        return MgoBcs.serializeToBase64(obj, serializer);
    }

    /**
     * Deserialize object from Base64 string.
     */
    public static <T> T deserializeFromBase64(String base64, BcsDeserializer.BcsTypeDeserializer<T> deserializer) throws IOException {
        return MgoBcs.deserializeFromBase64(base64, deserializer);
    }

    /**
     * Serialize object to Hex string.
     */
    public static <T> String serializeToHex(T obj, BcsSerializer.BcsTypeSerializer<T> serializer) throws IOException {
        return MgoBcs.serializeToHex(obj, serializer);
    }

    /**
     * Deserialize object from Hex string.
     */
    public static <T> T deserializeFromHex(String hex, BcsDeserializer.BcsTypeDeserializer<T> deserializer) throws IOException {
        return MgoBcs.deserializeFromHex(hex, deserializer);
    }
    
    /**
     * Serialize object to Base64.
     */
    public static <T> String serializeToBase64Direct(T obj, BcsSerializer.BcsTypeSerializer<T> serializer) throws IOException {
        BcsSerializer bcsSerializer = new BcsSerializer();
        serializer.serialize(bcsSerializer, obj);
        return bcsSerializer.toBase64();
    }
    
    /**
     * Deserialize object from Base64.
     */
    public static <T> T deserializeFromBase64Direct(String base64, Function<BcsDeserializer, T> deserializer) throws IOException {
        byte[] data = Base64.decode(base64);
        BcsDeserializer bcsDeserializer = new BcsDeserializer(data);
        return deserializer.apply(bcsDeserializer);
    }
    
    /**
     * Validate BCS serialization result.
     */
    public static <T> boolean validateSerialization(T obj, BcsSerializer.BcsTypeSerializer<T> serializer) {
        try {
            String base64 = serializeToBase64(obj, serializer);
            // Validate Base64 format.
            Base64.decode(base64);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the serialized byte array.
     */
    public static <T> byte[] serializeToBytes(T obj, BcsSerializer.BcsTypeSerializer<T> serializer) throws IOException {
        BcsSerializer bcsSerializer = new BcsSerializer();
        serializer.serialize(bcsSerializer, obj);
        return bcsSerializer.toByteArray();
    }
    
    /**
     * Deserialize object from byte array.
     */
    public static <T> T deserializeFromBytes(byte[] data, Function<BcsDeserializer, T> deserializer) throws IOException {
        BcsDeserializer bcsDeserializer = new BcsDeserializer(data);
        return deserializer.apply(bcsDeserializer);
    }
    
    // Static initialization block, registers all serializers.
    static {
        // Register primitive type serializers.
        registerSerializer(Byte.class, U8_SERIALIZER);
        registerSerializer(Short.class, U16_SERIALIZER);
        registerSerializer(Integer.class, U32_SERIALIZER);
        registerSerializer(Long.class, U64_SERIALIZER);
        registerSerializer(Boolean.class, BOOL_SERIALIZER);
        registerSerializer(String.class, STRING_SERIALIZER);
        registerSerializer(byte[].class, BYTE_ARRAY_SERIALIZER);

        // Register Mgo type serializers.
        registerSerializer(MgoObjectRef.class, MGO_OBJECT_REF_SERIALIZER);
        registerSerializer(SharedObjectRef.class, SHARED_OBJECT_REF_SERIALIZER);
        registerSerializer(ObjectArg.class, OBJECT_ARG_SERIALIZER);
        registerSerializer(CallArg.class, CALL_ARG_SERIALIZER);
        registerSerializer(TypeTag.class, TYPE_TAG_SERIALIZER);
        registerSerializer(TypeTagStructTag.class, STRUCT_TAG_SERIALIZER);
        registerSerializer(GasData.class, GAS_DATA_SERIALIZER);
        
        // Register primitive type serializers.
        registerSerializer(Argument.class, ARGUMENT_SERIALIZER);
        registerSerializer(ProgrammableMoveCall.class, PROGRAMMABLE_MOVE_CALL_SERIALIZER);
        registerSerializer(Command.class, COMMAND_SERIALIZER);
        registerSerializer(ProgrammableTransaction.class, PROGRAMMABLE_TRANSACTION_SERIALIZER);
        registerSerializer(TransactionKind.class, TRANSACTION_KIND_SERIALIZER);
        registerSerializer(TransactionExpiration.class, TRANSACTION_EXPIRATION_SERIALIZER);
        registerSerializer(TransactionDataV1.class, TRANSACTION_DATA_V1_SERIALIZER);
        registerSerializer(TransactionData.class, TRANSACTION_DATA_SERIALIZER);
        
        // Register Intent-related serializers.
        registerSerializer(IntentScope.class, INTENT_SCOPE_SERIALIZER);
        registerSerializer(IntentVersion.class, INTENT_VERSION_SERIALIZER);
        registerSerializer(AppId.class, APP_ID_SERIALIZER);
        registerSerializer(Intent.class, INTENT_SERIALIZER);

        // IntentMessage is a generic class and needs to be registered during concrete usage.
        
        // Register signature-related serializers.
        registerSerializer(CompressedSignature.class, COMPRESSED_SIGNATURE_SERIALIZER);
        registerSerializer(PublicKey.class, PUBLIC_KEY_SERIALIZER);
        registerSerializer(MultiSigPkMap.class, MULTI_SIG_PK_MAP_SERIALIZER);
        registerSerializer(MultiSigPublicKey.class, MULTI_SIG_PUBLIC_KEY_SERIALIZER);
        registerSerializer(MultiSig.class, MULTI_SIG_SERIALIZER);
        registerSerializer(SenderSignedTransaction.class, SENDER_SIGNED_TRANSACTION_SERIALIZER);
        
        // Register authentication-related serializers.
        registerSerializer(PasskeyAuthenticator.class, PASSKEY_AUTHENTICATOR_SERIALIZER);
        
        // Register signature-related serializers.
        registerSerializer(Owner.class, OWNER_SERIALIZER);
        
        // Register Effects type serializers.
        registerSerializer(GasCostSummary.class, EffectsBcs.GAS_COST_SUMMARY_SERIALIZER);
        registerSerializer(TransactionEffectsV1.class, EffectsBcs.TRANSACTION_EFFECTS_V1_SERIALIZER);
        registerSerializer(TransactionEffects.class, EffectsBcs.TRANSACTION_EFFECTS_SERIALIZER);
    }
} 