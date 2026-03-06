package io.mangonet.mgo.model.object.kind.owner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shared extends Owner {

    @JsonProperty("initial_shared_version")
    private BigInteger initialSharedVersion;

    public Shared() {
        this.type = "Shared";
    }
}
