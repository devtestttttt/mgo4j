package io.mangonet.mgo.model.move.kind;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mangonet.mgo.protocol.deserializer.MoveValueDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = MoveValueDeserializer.class)
public class MoveValue {

    private Object value;

    private MoveValueType valueType;

    public enum MoveValueType {
        UINT32, BOOLEAN, STRING, ARRAY, ADDRESS, STRUCT_OBJECT, ID_OBJECT, NULL, VARIANT
    }
}
