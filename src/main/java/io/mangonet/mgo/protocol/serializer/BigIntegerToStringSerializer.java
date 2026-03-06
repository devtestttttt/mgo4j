package io.mangonet.mgo.protocol.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigInteger;

public class BigIntegerToStringSerializer extends JsonSerializer<BigInteger> {

    @Override
    public void serialize(BigInteger value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Serialize BigInteger to string
        gen.writeString(value.toString());
    }

}
