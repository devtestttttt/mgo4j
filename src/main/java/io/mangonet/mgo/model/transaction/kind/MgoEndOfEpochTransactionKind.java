package io.mangonet.mgo.model.transaction.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.transaction.kind.epoch.*;
import io.mangonet.mgo.model.transaction.kind.epoch.*;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringEndOfEpoch.class, name = "StringType"),
        @JsonSubTypes.Type(value = ChangeEpoch.class, name = "ChangeEpoch"),
        @JsonSubTypes.Type(value = AuthenticatorStateExpire.class, name = "AuthenticatorStateExpire"),
        @JsonSubTypes.Type(value = BridgeStateCreate.class, name = "BridgeStateCreate"),
        @JsonSubTypes.Type(value = BridgeCommitteeUpdate.class, name = "BridgeCommitteeUpdate")
})
public abstract class MgoEndOfEpochTransactionKind {

    protected String type;

}
