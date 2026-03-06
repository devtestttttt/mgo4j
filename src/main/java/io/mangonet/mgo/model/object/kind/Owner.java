package io.mangonet.mgo.model.object.kind;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mangonet.mgo.protocol.deserializer.OwnerDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = OwnerDeserializer.class)
public abstract class Owner {

    protected String type;
}
