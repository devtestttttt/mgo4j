package io.mangonet.mgo.bcs.types.tag;

public class TypeTagAddress extends TypeTag {

    public static final TypeTagAddress INSTANCE = new TypeTagAddress();
    
    private TypeTagAddress() {}
    
    @Override
    public String toString() {
        return "address";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagAddress;
    }
    
    @Override
    public int hashCode() {
        return TypeTagAddress.class.hashCode();
    }
} 