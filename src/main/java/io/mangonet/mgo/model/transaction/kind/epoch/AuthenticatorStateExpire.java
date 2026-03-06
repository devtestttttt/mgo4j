package io.mangonet.mgo.model.transaction.kind.epoch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import io.mangonet.mgo.model.transaction.kind.epoch.subtypes.MgoAuthenticatorStateExpire;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatorStateExpire extends MgoEndOfEpochTransactionKind {

    @JsonProperty("AuthenticatorStateExpire")
    private MgoAuthenticatorStateExpire authStateExpire;

    public AuthenticatorStateExpire() {
        this.type = "AuthenticatorStateExpire";
    }
}
