package io.mangonet.mgo.bcs.types.tag;

public class TypeTagU32 extends TypeTag {

    public static final TypeTagU32 INSTANCE = new TypeTagU32();
    
    private TypeTagU32() {}
    
    @Override
    public String toString() {
        return "u32";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagU32;
    }
    
    @Override
    public int hashCode() {
        return TypeTagU32.class.hashCode();
    }
} 