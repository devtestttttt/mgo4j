package io.mangonet.mgo.model.move.kind;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mangonet.mgo.protocol.deserializer.MoveStructDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = MoveStructDeserializer.class)
public abstract class MoveStruct {

}
