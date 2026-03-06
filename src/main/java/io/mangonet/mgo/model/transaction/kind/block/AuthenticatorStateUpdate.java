package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.MgoActiveJwk;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatorStateUpdate extends TransactionBlockKind {

    private BigInteger epoch;

    @JsonProperty("new_active_jwks")
    private List<MgoActiveJwk> newActiveJwks;

    private BigInteger round;

    public AuthenticatorStateUpdate() {
        this.kind = "AuthenticatorStateUpdate";
    }
}
