package io.mangonet.mgo.bcs.types.intent;

public abstract class IntentScope {

    /**
     * get IntentScope
     * @return
     */
    public abstract byte[] getScope();

    /**
     * transaction data
     */
    public static class TransactionData extends IntentScope {

        public static final TransactionData INSTANCE = new TransactionData();
        public static final byte[] INSTANCE_BYTES = new byte[]{0x00, 0x00, 0x00};
        
        private TransactionData() {}

        @Override
        public byte[] getScope() {
            return INSTANCE_BYTES;
        }

        @Override
        public String toString() {
            return "TransactionData";
        }
    }
    
    /**
     * transaction effects
     */
    public static class TransactionEffects extends IntentScope {

        public static final TransactionEffects INSTANCE = new TransactionEffects();
        public static final byte[] INSTANCE_BYTES = new byte[]{0x01, 0x00, 0x00};
        
        private TransactionEffects() {}

        @Override
        public byte[] getScope() {
            return INSTANCE_BYTES;
        }
        
        @Override
        public String toString() {
            return "TransactionEffects";
        }
    }
    
    /**
     * checkpoint summary
     */
    public static class CheckpointSummary extends IntentScope {

        public static final CheckpointSummary INSTANCE = new CheckpointSummary();
        public static final byte[] INSTANCE_BYTES = new byte[]{0x02, 0x00, 0x00};
        
        private CheckpointSummary() {}

        @Override
        public byte[] getScope() {
            return INSTANCE_BYTES;
        }
        
        @Override
        public String toString() {
            return "CheckpointSummary";
        }
    }
    
    /**
     * personal message
     */
    public static class PersonalMessage extends IntentScope {

        public static final PersonalMessage INSTANCE = new PersonalMessage();
        public static final byte[] INSTANCE_BYTES = new byte[]{0x03, 0x00, 0x00};
        
        private PersonalMessage() {}

        @Override
        public byte[] getScope() {
            return INSTANCE_BYTES;
        }
        
        @Override
        public String toString() {
            return "PersonalMessage";
        }
    }
} 