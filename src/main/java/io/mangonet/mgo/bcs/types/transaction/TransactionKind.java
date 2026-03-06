package io.mangonet.mgo.bcs.types.transaction;

import java.util.Objects;

public abstract class TransactionKind {
    
    /**
     * Programmable transaction
     */
    public static class ProgrammableTransaction extends TransactionKind {
        private final io.mangonet.mgo.bcs.types.transaction.ProgrammableTransaction programmableTransaction;
        
        public ProgrammableTransaction(io.mangonet.mgo.bcs.types.transaction.ProgrammableTransaction programmableTransaction) {
            this.programmableTransaction = Objects.requireNonNull(programmableTransaction);
        }
        
        public io.mangonet.mgo.bcs.types.transaction.ProgrammableTransaction getProgrammableTransaction() {
            return programmableTransaction;
        }
        
        @Override
        public String toString() {
            return "ProgrammableTransaction{" + programmableTransaction + "}";
        }
    }
    
    /**
     * ChangeEpoch
     */
    public static class ChangeEpoch extends TransactionKind {
        public static final ChangeEpoch INSTANCE = new ChangeEpoch();
        
        private ChangeEpoch() {}
        
        @Override
        public String toString() {
            return "ChangeEpoch";
        }
    }
    
    /**
     * Genesis
     */
    public static class Genesis extends TransactionKind {
        public static final Genesis INSTANCE = new Genesis();
        
        private Genesis() {}
        
        @Override
        public String toString() {
            return "Genesis";
        }
    }
    
    /**
     * Consensus commit prologue
     */
    public static class ConsensusCommitPrologue extends TransactionKind {
        public static final ConsensusCommitPrologue INSTANCE = new ConsensusCommitPrologue();
        
        private ConsensusCommitPrologue() {}
        
        @Override
        public String toString() {
            return "ConsensusCommitPrologue";
        }
    }
} 