package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsensusCommitPrologue extends TransactionBlockKind {

    @JsonProperty("commit_timestamp_ms")
    private BigInteger commitTimestampMs;

    private BigInteger epoch;

    private BigInteger round;

    public ConsensusCommitPrologue() {
        this.kind = "ConsensusCommitPrologue";
    }
}
