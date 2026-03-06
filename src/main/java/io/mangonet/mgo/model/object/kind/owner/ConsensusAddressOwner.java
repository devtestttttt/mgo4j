package io.mangonet.mgo.model.object.kind.owner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.Owner;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsensusAddressOwner extends Owner {

    @JsonProperty("ConsensusAddressOwner")
    private ConsensusData consensus;

    public ConsensusAddressOwner() {
        this.type = "ConsensusAddressOwner";
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConsensusData {

        private String owner;

        @JsonProperty("start_version")
        private BigInteger startVersion;
    }
}
