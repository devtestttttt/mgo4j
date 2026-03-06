package io.mangonet.mgo.bcs.types.tag;

public class TypeTagU8 extends TypeTag {

    public static final TypeTagU8 INSTANCE = new TypeTagU8();
    
    private TypeTagU8() {}
    
    @Override
    public String toString() {
        return "u8";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagU8;
    }
    
    @Override
    public int hashCode() {
        return TypeTagU8.class.hashCode();
    }
} 