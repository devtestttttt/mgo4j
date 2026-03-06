package io.mangonet.mgo.bcs.types.transaction;

import io.mangonet.mgo.bcs.types.gas.GasData;

import java.util.Objects;

public class TransactionDataV1 {
    
    private final TransactionKind kind;
    private final String sender;
    private final GasData gasData;
    private final TransactionExpiration expiration;
    
    public TransactionDataV1(TransactionKind kind, String sender, GasData gasData, TransactionExpiration expiration) {
        this.kind = Objects.requireNonNull(kind);
        this.sender = Objects.requireNonNull(sender);
        this.gasData = Objects.requireNonNull(gasData);
        this.expiration = Objects.requireNonNull(expiration);
    }
    
    public TransactionKind getKind() {
        return kind;
    }
    
    public String getSender() {
        return sender;
    }
    
    public GasData getGasData() {
        return gasData;
    }
    
    public TransactionExpiration getExpiration() {
        return expiration;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TransactionDataV1 that = (TransactionDataV1) obj;
        return Objects.equals(kind, that.kind) &&
               Objects.equals(sender, that.sender) &&
               Objects.equals(gasData, that.gasData) &&
               Objects.equals(expiration, that.expiration);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(kind, sender, gasData, expiration);
    }
    
    @Override
    public String toString() {
        return "TransactionDataV1{" +
               "kind=" + kind +
               ", sender='" + sender + '\'' +
               ", gasData=" + gasData +
               ", expiration=" + expiration +
               '}';
    }
} 