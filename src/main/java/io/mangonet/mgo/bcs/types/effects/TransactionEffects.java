package io.mangonet.mgo.bcs.types.effects;

public class TransactionEffects {

    private final TransactionEffectsV1 v1;
    
    public TransactionEffects(TransactionEffectsV1 v1) {
        this.v1 = v1;
    }
    
    public TransactionEffectsV1 getV1() {
        return v1;
    }
} 