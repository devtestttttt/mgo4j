package io.mangonet.mgo.bcs.types.tag;

public class TypeTagU256 extends TypeTag {

    public static final TypeTagU256 INSTANCE = new TypeTagU256();
    
    private TypeTagU256() {}
    
    @Override
    public String toString() {
        return "u256";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagU256;
    }
    
    @Override
    public int hashCode() {
        return TypeTagU256.class.hashCode();
    }
} 