package io.mangonet.mgo.model.transaction.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.transaction.kind.status.Failure;
import io.mangonet.mgo.model.transaction.kind.status.Success;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "status",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Success.class, name = "success"),
        @JsonSubTypes.Type(value = Failure.class, name = "failure")
})
public interface ExecutionStatus {

    String getStatus();
}
