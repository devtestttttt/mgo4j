package io.mangonet.mgo.bcs.types.intent;

public abstract class AppId {
    
    /**
     * Mgo application.
     */
    public static class Mgo extends AppId {
        public static final Mgo INSTANCE = new Mgo();
        
        private Mgo() {}
        
        @Override
        public String toString() {
            return "Mgo";
        }
    }
} 