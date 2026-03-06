package io.mangonet.mgo.bcs.types.effects;

import lombok.ToString;

@ToString
public class GasCostSummary {

    private final long computationCost;
    private final long storageCost;
    private final long storageRebate;
    private final long nonRefundableStorageFee;
    
    public GasCostSummary(long computationCost, long storageCost, long storageRebate, long nonRefundableStorageFee) {
        this.computationCost = computationCost;
        this.storageCost = storageCost;
        this.storageRebate = storageRebate;
        this.nonRefundableStorageFee = nonRefundableStorageFee;
    }
    
    public long getComputationCost() {
        return computationCost;
    }
    
    public long getStorageCost() {
        return storageCost;
    }
    
    public long getStorageRebate() {
        return storageRebate;
    }
    
    public long getNonRefundableStorageFee() {
        return nonRefundableStorageFee;
    }
} 