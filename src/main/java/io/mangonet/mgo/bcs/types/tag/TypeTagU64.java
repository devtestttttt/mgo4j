package io.mangonet.mgo.bcs.types.tag;

public class TypeTagU64 extends TypeTag {

    public static final TypeTagU64 INSTANCE = new TypeTagU64();
    
    public TypeTagU64() {}
    
    @Override
    public String toString() {
        return "u64";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagU64;
    }
    
    @Override
    public int hashCode() {
        return TypeTagU64.class.hashCode();
    }
} 