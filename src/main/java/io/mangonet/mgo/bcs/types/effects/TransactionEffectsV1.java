package io.mangonet.mgo.bcs.types.effects;

import io.mangonet.mgo.bcs.types.gas.MgoObjectRef;
import lombok.ToString;

import java.util.List;

@ToString
public class TransactionEffectsV1 {

    private final ExecutionStatus status;
    private final long executedEpoch;
    private final GasCostSummary gasUsed;
    private final List<MgoObjectRef> created;
    private final List<MgoObjectRef> mutated;
    private final List<MgoObjectRef> deleted;
    private final String transactionDigest;
    
    public TransactionEffectsV1(ExecutionStatus status, long executedEpoch, GasCostSummary gasUsed,
                                List<MgoObjectRef> created, List<MgoObjectRef> mutated, List<MgoObjectRef> deleted,
                                String transactionDigest) {
        this.status = status;
        this.executedEpoch = executedEpoch;
        this.gasUsed = gasUsed;
        this.created = created;
        this.mutated = mutated;
        this.deleted = deleted;
        this.transactionDigest = transactionDigest;
    }
    
    public ExecutionStatus getStatus() {
        return status;
    }
    
    public long getExecutedEpoch() {
        return executedEpoch;
    }
    
    public GasCostSummary getGasUsed() {
        return gasUsed;
    }
    
    public List<MgoObjectRef> getCreated() {
        return created;
    }
    
    public List<MgoObjectRef> getMutated() {
        return mutated;
    }
    
    public List<MgoObjectRef> getDeleted() {
        return deleted;
    }
    
    public String getTransactionDigest() {
        return transactionDigest;
    }
} 