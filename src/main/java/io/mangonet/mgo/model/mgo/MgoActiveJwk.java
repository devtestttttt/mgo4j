package io.mangonet.mgo.model.mgo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoActiveJwk {

    @JsonProperty("epoch")
    private BigInteger epoch;

    @JsonProperty("jwk")
    private MgoJWK jwk;

    @JsonProperty("jwk_id")
    private MgoJwkId jwkId;
}