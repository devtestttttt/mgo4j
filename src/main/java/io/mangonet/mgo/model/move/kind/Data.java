package io.mangonet.mgo.model.move.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.move.kind.data.MoveObject;
import io.mangonet.mgo.model.move.kind.data.Package;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "dataType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MoveObject.class, name = "moveObject"),
        @JsonSubTypes.Type(value = Package.class, name = "package")
})
public interface Data {

}
