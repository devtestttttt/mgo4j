package io.mangonet.mgo.model.move.kind;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mangonet.mgo.protocol.deserializer.MoveFunctionArgTypeDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = MoveFunctionArgTypeDeserializer.class)
public abstract class MoveFunctionArgType {

    protected String type;
}
