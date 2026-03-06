package io.mangonet.mgo.bcs.types.tag;

import java.util.Objects;

public class TypeTagStruct extends TypeTag {

    private final TypeTagStructTag structTag;
    
    public TypeTagStruct(TypeTagStructTag structTag) {
        this.structTag = Objects.requireNonNull(structTag);
    }
    
    public TypeTagStructTag getStructTag() {
        return structTag;
    }
    
    @Override
    public String toString() {
        return structTag.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TypeTagStruct struct = (TypeTagStruct) obj;
        return Objects.equals(structTag, struct.structTag);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(structTag);
    }
} 