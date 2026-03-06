package io.mangonet.mgo.bcs.types.signature;

import java.util.Objects;

public class MultiSigPkMap {
    
    private final PublicKey pubKey;
    private final int weight;
    
    public MultiSigPkMap(PublicKey pubKey, int weight) {
        this.pubKey = Objects.requireNonNull(pubKey);
        this.weight = weight;
    }
    
    public PublicKey getPubKey() {
        return pubKey;
    }
    
    public int getWeight() {
        return weight;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MultiSigPkMap that = (MultiSigPkMap) obj;
        return weight == that.weight &&
               Objects.equals(pubKey, that.pubKey);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pubKey, weight);
    }
    
    @Override
    public String toString() {
        return "MultiSigPkMap{" +
               "pubKey=" + pubKey +
               ", weight=" + weight +
               '}';
    }
} 