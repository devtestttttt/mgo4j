package io.mangonet.mgo.model.object.kind.input.subtypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SharedMoveObjectDetail {

    private String id;

    @JsonProperty("initial_shared_version")
    private BigInteger initialSharedVersion;

    private Boolean mutable;
}
