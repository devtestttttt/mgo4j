package io.mangonet.mgo.model.object.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.object.kind.error.*;
import io.mangonet.mgo.model.object.kind.error.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "code"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NotExists.class, name = "notExists"),
        @JsonSubTypes.Type(value = DynamicFieldNotFound.class, name = "dynamicFieldNotFound"),
        @JsonSubTypes.Type(value = Deleted.class, name = "deleted"),
        @JsonSubTypes.Type(value = Unknown.class, name = "unknown"),
        @JsonSubTypes.Type(value = Display.class, name = "displayError")
})
public interface ObjectResponseError {

    String getCode();

}