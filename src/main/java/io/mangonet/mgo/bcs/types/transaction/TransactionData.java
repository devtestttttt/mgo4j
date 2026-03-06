package io.mangonet.mgo.bcs.types.transaction;

import java.util.Objects;

public abstract class TransactionData {
    
    /**
     * V1 version transaction data
     */
    public static class V1 extends TransactionData {
        private final TransactionDataV1 transactionDataV1;
        
        public V1(TransactionDataV1 transactionDataV1) {
            this.transactionDataV1 = Objects.requireNonNull(transactionDataV1);
        }
        
        public TransactionDataV1 getTransactionDataV1() {
            return transactionDataV1;
        }
        
        @Override
        public String toString() {
            return "V1{" + transactionDataV1 + "}";
        }
    }
} 