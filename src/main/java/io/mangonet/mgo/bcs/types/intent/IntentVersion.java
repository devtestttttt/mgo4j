package io.mangonet.mgo.bcs.types.intent;

public abstract class IntentVersion {
    
    /**
     * V0 version
     */
    public static class V0 extends IntentVersion {
        public static final V0 INSTANCE = new V0();
        
        private V0() {}
        
        @Override
        public String toString() {
            return "V0";
        }
    }
} 