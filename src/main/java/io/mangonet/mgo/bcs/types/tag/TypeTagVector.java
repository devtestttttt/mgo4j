package io.mangonet.mgo.bcs.types.tag;

import java.util.Objects;

public class TypeTagVector extends TypeTag {

    private final TypeTag elementType;
    
    public TypeTagVector(TypeTag elementType) {
        this.elementType = Objects.requireNonNull(elementType);
    }
    
    public TypeTag getElementType() {
        return elementType;
    }
    
    @Override
    public String toString() {
        return "vector<" + elementType + ">";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TypeTagVector vector = (TypeTagVector) obj;
        return Objects.equals(elementType, vector.elementType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(elementType);
    }
} 