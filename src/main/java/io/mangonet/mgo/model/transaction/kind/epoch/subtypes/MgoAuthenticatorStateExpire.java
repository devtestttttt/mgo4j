package io.mangonet.mgo.model.transaction.kind.epoch.subtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
public class MgoAuthenticatorStateExpire {

    @JsonProperty("min_epoch")
    private BigInteger minEpoch; // BigInt_for_uint64
}
