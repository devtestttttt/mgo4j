package io.mangonet.mgo.model.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceChange {

    @JsonProperty("amount")
    private BigInteger amount;

    @JsonProperty("coinType")
    private String coinType;

    @JsonProperty("owner")
    private Owner owner;
}
