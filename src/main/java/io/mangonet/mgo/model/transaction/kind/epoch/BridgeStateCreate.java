package io.mangonet.mgo.model.transaction.kind.epoch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BridgeStateCreate extends MgoEndOfEpochTransactionKind {

    @JsonProperty("BridgeStateCreate")
    private String checkpointDigest;

    public BridgeStateCreate() {
        this.type = "BridgeStateCreate";
    }
}
