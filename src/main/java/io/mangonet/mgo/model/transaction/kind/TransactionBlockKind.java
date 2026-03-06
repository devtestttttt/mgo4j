package io.mangonet.mgo.model.transaction.kind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mangonet.mgo.model.transaction.kind.block.*;
import io.mangonet.mgo.model.transaction.kind.block.*;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "kind",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChangeEpoch.class, name = "ChangeEpoch"),
        @JsonSubTypes.Type(value = Genesis.class, name = "Genesis"),
        @JsonSubTypes.Type(value = ConsensusCommitPrologue.class, name = "ConsensusCommitPrologue"),
        @JsonSubTypes.Type(value = ProgrammableTransaction.class, name = "ProgrammableTransaction"),
        @JsonSubTypes.Type(value = AuthenticatorStateUpdate.class, name = "AuthenticatorStateUpdate"),
        @JsonSubTypes.Type(value = RandomnessStateUpdate.class, name = "RandomnessStateUpdate"),
        @JsonSubTypes.Type(value = EndOfEpochTransaction.class, name = "EndOfEpochTransaction"),
        @JsonSubTypes.Type(value = ConsensusCommitPrologueV2.class, name = "ConsensusCommitPrologueV2"),
        @JsonSubTypes.Type(value = ConsensusCommitPrologueV3.class, name = "ConsensusCommitPrologueV3"),
        @JsonSubTypes.Type(value = ConsensusCommitPrologueV4.class, name = "ConsensusCommitPrologueV4")
})
public abstract class TransactionBlockKind {

    protected String kind;

}
