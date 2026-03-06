package io.mangonet.mgo.model.object.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.object.kind.input.ImmOrOwnedMoveObject;
import io.mangonet.mgo.model.object.kind.input.MovePackage;
import io.mangonet.mgo.model.object.kind.input.SharedMoveObject;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MovePackage.class, name = "MovePackage"),
        @JsonSubTypes.Type(value = ImmOrOwnedMoveObject.class, name = "ImmOrOwnedMoveObject"),
        @JsonSubTypes.Type(value = SharedMoveObject.class, name = "SharedMoveObject")
})
public interface InputObjectKind {

    String getType();
}
