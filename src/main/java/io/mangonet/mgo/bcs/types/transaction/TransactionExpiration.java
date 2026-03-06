package io.mangonet.mgo.bcs.types.transaction;

import java.util.Objects;

public abstract class TransactionExpiration {
    
    /**
     * none
     */
    public static class None extends TransactionExpiration {
        public static final None INSTANCE = new None();
        
        private None() {}
        
        @Override
        public String toString() {
            return "None";
        }
    }
    
    /**
     * epoch
     */
    public static class Epoch extends TransactionExpiration {
        private final long epoch;
        
        public Epoch(long epoch) {
            this.epoch = epoch;
        }
        
        public long getEpoch() {
            return epoch;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Epoch epoch1 = (Epoch) obj;
            return epoch == epoch1.epoch;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(epoch);
        }
        
        @Override
        public String toString() {
            return "Epoch{" + epoch + "}";
        }
    }
} 