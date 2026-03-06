package io.mangonet.mgo.bcs.types.gas;

import lombok.ToString;

import java.util.Objects;

@ToString
public class MgoObjectRef {

    private final String objectId;
    private final long version;
    private final String digest;
    
    public MgoObjectRef(String objectId, long version, String digest) {
        this.objectId = Objects.requireNonNull(objectId);
        this.version = version;
        this.digest = Objects.requireNonNull(digest);
    }
    
    public String getObjectId() {
        return objectId;
    }
    
    public long getVersion() {
        return version;
    }
    
    public String getDigest() {
        return digest;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MgoObjectRef that = (MgoObjectRef) obj;
        return version == that.version &&
               Objects.equals(objectId, that.objectId) &&
               Objects.equals(digest, that.digest);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(objectId, version, digest);
    }
    
    @Override
    public String toString() {
        return "MgoObjectRef{" +
               "objectId='" + objectId + '\'' +
               ", version=" + version +
               ", digest='" + digest + '\'' +
               '}';
    }
} 