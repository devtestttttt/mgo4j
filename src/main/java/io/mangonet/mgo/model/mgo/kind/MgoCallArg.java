package io.mangonet.mgo.model.mgo.kind;

import io.mangonet.mgo.model.mgo.kind.call.ImmOrOwnedObject;
import io.mangonet.mgo.model.mgo.kind.call.PureCall;
import io.mangonet.mgo.model.mgo.kind.call.ReceivingObject;
import io.mangonet.mgo.model.mgo.kind.call.SharedObject;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImmOrOwnedObject.class, name = "object"),
        @JsonSubTypes.Type(value = SharedObject.class, name = "object"),
        @JsonSubTypes.Type(value = ReceivingObject.class, name = "object"),
        @JsonSubTypes.Type(value = PureCall.class, name = "pure")
})
public abstract class MgoCallArg {

    protected String type;

}
