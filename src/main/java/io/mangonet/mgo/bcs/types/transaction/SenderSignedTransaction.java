package io.mangonet.mgo.bcs.types.transaction;

import io.mangonet.mgo.bcs.types.intent.IntentMessage;

import java.util.List;
import java.util.Objects;

public class SenderSignedTransaction {
    
    private final IntentMessage<TransactionData> intentMessage;
    private final List<byte[]> txSignatures;
    
    public SenderSignedTransaction(IntentMessage<TransactionData> intentMessage, List<byte[]> txSignatures) {
        this.intentMessage = Objects.requireNonNull(intentMessage);
        this.txSignatures = Objects.requireNonNull(txSignatures);
    }
    
    public IntentMessage<TransactionData> getIntentMessage() {
        return intentMessage;
    }
    
    public List<byte[]> getTxSignatures() {
        return txSignatures;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SenderSignedTransaction that = (SenderSignedTransaction) obj;
        return Objects.equals(intentMessage, that.intentMessage) &&
               Objects.equals(txSignatures, that.txSignatures);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(intentMessage, txSignatures);
    }
    
    @Override
    public String toString() {
        return "SenderSignedTransaction{" +
               "intentMessage=" + intentMessage +
               ", txSignatures=" + txSignatures.size() + " signatures" +
               '}';
    }
} 