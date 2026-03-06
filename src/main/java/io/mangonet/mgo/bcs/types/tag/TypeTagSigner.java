package io.mangonet.mgo.bcs.types.tag;

public class TypeTagSigner extends TypeTag {

    public static final TypeTagSigner INSTANCE = new TypeTagSigner();
    
    private TypeTagSigner() {}
    
    @Override
    public String toString() {
        return "signer";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagSigner;
    }
    
    @Override
    public int hashCode() {
        return TypeTagSigner.class.hashCode();
    }
} 