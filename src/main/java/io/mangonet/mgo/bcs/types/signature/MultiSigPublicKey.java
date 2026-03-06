package io.mangonet.mgo.bcs.types.signature;

import java.util.List;
import java.util.Objects;

public class MultiSigPublicKey {
    
    private final List<MultiSigPkMap> pkMap;
    private final int threshold;
    
    public MultiSigPublicKey(List<MultiSigPkMap> pkMap, int threshold) {
        this.pkMap = Objects.requireNonNull(pkMap);
        this.threshold = threshold;
    }
    
    public List<MultiSigPkMap> getPkMap() {
        return pkMap;
    }
    
    public int getThreshold() {
        return threshold;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MultiSigPublicKey that = (MultiSigPublicKey) obj;
        return threshold == that.threshold &&
               Objects.equals(pkMap, that.pkMap);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pkMap, threshold);
    }
    
    @Override
    public String toString() {
        return "MultiSigPublicKey{" +
               "pkMap=" + pkMap +
               ", threshold=" + threshold +
               '}';
    }
} 