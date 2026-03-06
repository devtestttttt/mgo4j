package io.mangonet.mgo.protocol.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigInteger;

public class StringToBigIntegerDeserializer extends JsonDeserializer<BigInteger> {

    @Override
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == JsonToken.VALUE_STRING) {
            return new BigInteger(p.getText());
        } else if (p.currentToken() == JsonToken.VALUE_NUMBER_INT) {
            return BigInteger.valueOf(p.getLongValue());
        }
        throw ctxt.wrongTokenException(p, BigInteger.class, JsonToken.VALUE_STRING,
                "Expected string or number for BigInteger value");
    }
}
