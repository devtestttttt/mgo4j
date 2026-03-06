package io.mangonet.mgo.model.move.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.move.kind.rawdata.MoveObjectRaw;
import io.mangonet.mgo.model.move.kind.rawdata.PackageRaw;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "dataType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MoveObjectRaw.class, name = "moveObject"),
        @JsonSubTypes.Type(value = PackageRaw.class, name = "package")
})
public interface RawData {

    String getDataType();

    String getVersion();
}
