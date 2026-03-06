package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsensusCommitPrologueV4 extends TransactionBlockKind {

    @JsonProperty("additional_state_digest")
    private String additionalStateDigest;

    @JsonProperty("commit_timestamp_ms")
    private BigInteger commitTimestampMs;

    @JsonProperty("consensus_commit_digest")
    private String consensusCommitDigest;

    @JsonProperty("consensus_determined_version_assignments")
    private String consensusDeterminedVersionAssignments;

    private BigInteger epoch;

    private BigInteger round;

    @JsonProperty("sub_dag_index")
    private BigInteger subDagIndex;

    public ConsensusCommitPrologueV4() {
        this.kind = "ConsensusCommitPrologueV4";
    }
}
