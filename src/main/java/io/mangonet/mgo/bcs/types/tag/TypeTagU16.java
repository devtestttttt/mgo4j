package io.mangonet.mgo.bcs.types.tag;

public class TypeTagU16 extends TypeTag {

    public static final TypeTagU16 INSTANCE = new TypeTagU16();
    
    private TypeTagU16() {}
    
    @Override
    public String toString() {
        return "u16";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagU16;
    }
    
    @Override
    public int hashCode() {
        return TypeTagU16.class.hashCode();
    }
} 