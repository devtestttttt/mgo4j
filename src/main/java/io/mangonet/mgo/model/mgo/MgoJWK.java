package io.mangonet.mgo.model.mgo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoJWK {

    @JsonProperty("alg")
    private String alg;

    @JsonProperty("e")
    private String e;

    @JsonProperty("kty")
    private String kty;

    @JsonProperty("n")
    private String n;
}
