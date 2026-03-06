package io.mangonet.mgo.bcs.types.tag;

public class TypeTagU128 extends TypeTag {

    public static final TypeTagU128 INSTANCE = new TypeTagU128();
    
    private TypeTagU128() {}
    
    @Override
    public String toString() {
        return "u128";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagU128;
    }
    
    @Override
    public int hashCode() {
        return TypeTagU128.class.hashCode();
    }
} 