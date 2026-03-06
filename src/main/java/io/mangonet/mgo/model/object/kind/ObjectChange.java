package io.mangonet.mgo.model.object.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.object.kind.change.*;
import io.mangonet.mgo.model.object.kind.change.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Published.class, name = "published"),
        @JsonSubTypes.Type(value = Transferred.class, name = "transferred"),
        @JsonSubTypes.Type(value = Mutated.class, name = "mutated"),
        @JsonSubTypes.Type(value = Deleted.class, name = "deleted"),
        @JsonSubTypes.Type(value = Wrapped.class, name = "wrapped"),
        @JsonSubTypes.Type(value = Created.class, name = "created")
})
public interface ObjectChange {

    String getType();

    String getObjectId(); // public method, implemented by some subclasses

    Long getVersion();

}
