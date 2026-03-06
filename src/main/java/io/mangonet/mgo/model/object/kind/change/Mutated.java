package io.mangonet.mgo.model.object.kind.change;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mutated implements ObjectChange {

    private String digest;

    @JsonProperty("objectId")
    private String objectId;

    @JsonProperty("objectType")
    private String objectType;

    private Owner owner;

    @JsonProperty("previousVersion")
    private BigInteger previousVersion;

    private String sender;

    private final String type = "mutated";

    private Long version;
}
