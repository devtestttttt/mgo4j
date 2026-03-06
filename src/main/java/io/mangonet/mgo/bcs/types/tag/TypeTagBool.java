package io.mangonet.mgo.bcs.types.tag;

public class TypeTagBool extends TypeTag {

    public static final TypeTagBool INSTANCE = new TypeTagBool();
    
    public TypeTagBool() {}
    
    @Override
    public String toString() {
        return "bool";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeTagBool;
    }
    
    @Override
    public int hashCode() {
        return TypeTagBool.class.hashCode();
    }
} 