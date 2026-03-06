package io.mangonet.mgo.model.move.kind;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mangonet.mgo.protocol.deserializer.MgoMoveNormalizedTypeDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = MgoMoveNormalizedTypeDeserializer.class)
public abstract class MgoMoveNormalizedType {

    protected String type;
}
