package io.mangonet.mgo.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.mangonet.mgo.model.move.MgoMoveNormalizedModule;
import io.mangonet.mgo.model.move.kind.*;
import io.mangonet.mgo.model.move.kind.*;
import io.mangonet.mgo.model.object.kind.InputObjectKind;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import io.mangonet.mgo.model.object.kind.ObjectResponseError;
import io.mangonet.mgo.model.object.kind.Owner;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import io.mangonet.mgo.model.mgo.kind.MgoCallArg;
import io.mangonet.mgo.model.transaction.kind.ExecutionStatus;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import io.mangonet.mgo.protocol.deserializer.*;
import io.mangonet.mgo.protocol.deserializer.*;
import io.mangonet.mgo.protocol.serializer.BigIntegerToStringSerializer;
import io.mangonet.mgo.protocol.serializer.ByteArrayToU8ListSerializer;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ObjectMapperFactory {

    // Use double-checked locking to ensure thread safety
    private static volatile ObjectMapper DEFAULT_OBJECT_MAPPER;

    // Deserializer cache (Key: transaction type kind, Value: deserializer instance)
    private static final ConcurrentMap<String, JsonDeserializer<?>> DESERIALIZER_CACHE =
            new ConcurrentHashMap<>();

    static {
        initializeMappers();
    }

    private static void initializeMappers() {
        // Initialize defaultMapper (lazy loading)
        if (DEFAULT_OBJECT_MAPPER == null) {
            synchronized (ObjectMapperFactory.class) {
                if (DEFAULT_OBJECT_MAPPER == null) {
                    DEFAULT_OBJECT_MAPPER = createBaseMapper();
                }
            }
        }
    }

    public static ObjectMapper getObjectMapper() {
        return DEFAULT_OBJECT_MAPPER;
    }

    public static ObjectReader getObjectReader() {
        return DEFAULT_OBJECT_MAPPER.reader();
    }

    private static ObjectMapper createBaseMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Basic configuration
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // handle [] to null
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Register Mgo-specific modules (including cached deserializer)
        mapper.registerModule(new SimpleModule("MgoModule")
                // byte[]
                .addSerializer(byte[].class, new ByteArrayToU8ListSerializer())

                // BigInteger
                .addSerializer(BigInteger.class, new BigIntegerToStringSerializer())
                .addDeserializer(BigInteger.class, new StringToBigIntegerDeserializer())

                // Handle JSON parsing for various data types like field, object
                .addDeserializer(TransactionBlockKind.class, new TransactionBlockKindDeserializer())
                .addDeserializer(MgoEndOfEpochTransactionKind.class, new MgoEndOfEpochTransactionKindDeserializer())
                .addDeserializer(MgoArgument.class, new MgoArgumentDeserializer())
                .addDeserializer(MgoCallArg.class, new MgoCallArgDeserializer())
                .addDeserializer(ObjectChange.class, new ObjectChangeDeserializer())
                .addDeserializer(Owner.class, new OwnerDeserializer())
                .addDeserializer(ExecutionStatus.class, new ExecutionStatusDeserializer())
                .addDeserializer(InputObjectKind.class, new InputObjectKindDeserializer())
                .addDeserializer(MoveFunctionArgType.class, new MoveFunctionArgTypeDeserializer())
                .addDeserializer(MgoMoveNormalizedType.class, new MgoMoveNormalizedTypeDeserializer())
                .addDeserializer(MgoMoveNormalizedModule.class, new MgoMoveNormalizedModuleDeserializer())
                .addDeserializer(MoveStruct.class, new MoveStructDeserializer())
                .addDeserializer(Data.class, new DataDeserializer())
                .addDeserializer(RawData.class, new RawDataDeserializer())
                .addDeserializer(ObjectResponseError.class, new ObjectResponseErrorDeserializer())

                // Process special objects in various array formats such as `['', [obj]]`, `[[obj], '']`, or `[obj, [obj]]`.
                .addDeserializer(MgoTransaction.class, new MgoTransactionDeserializer())
                .addDeserializer(MoveValue.class, new MoveValueDeserializer())

                // Process the special single object in the format `{ objectName: "field" }`.
//                .addDeserializer(AddressOwner.class, new AddressOwnerDeserializer())
//                .addDeserializer(ObjectOwner.class, new ObjectOwnerDeserializer())
        );

        // Performance optimization configuration
        mapper.enable(JsonParser.Feature.USE_FAST_DOUBLE_PARSER)
                .enable(JsonParser.Feature.USE_FAST_BIG_NUMBER_PARSER)
                .disable(MapperFeature.AUTO_DETECT_CREATORS);

        return mapper;
    }

}
