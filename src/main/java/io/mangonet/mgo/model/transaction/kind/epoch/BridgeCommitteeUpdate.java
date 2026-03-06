package io.mangonet.mgo.model.transaction.kind.epoch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BridgeCommitteeUpdate extends MgoEndOfEpochTransactionKind {

    @JsonProperty("BridgeCommitteeUpdate")
    private BigInteger sequenceNumber;

    public BridgeCommitteeUpdate() {
        this.type = "BridgeCommitteeUpdate";
    }
}
