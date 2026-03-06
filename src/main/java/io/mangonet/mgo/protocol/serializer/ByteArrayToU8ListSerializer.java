package io.mangonet.mgo.protocol.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ByteArrayToU8ListSerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (byte b : value) {
            gen.writeNumber(b & 0xFF); // Prevent negative numbers from affecting serialization
        }
        gen.writeEndArray();
    }
}