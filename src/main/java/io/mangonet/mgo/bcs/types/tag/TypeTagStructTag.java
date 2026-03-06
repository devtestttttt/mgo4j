package io.mangonet.mgo.bcs.types.tag;

import java.util.List;
import java.util.Objects;

public class TypeTagStructTag {

    private final String address;
    private final String module;
    private final String name;
    private final List<TypeTag> typeParams;
    
    public TypeTagStructTag(String address, String module, String name, List<TypeTag> typeParams) {
        this.address = Objects.requireNonNull(address);
        this.module = Objects.requireNonNull(module);
        this.name = Objects.requireNonNull(name);
        this.typeParams = Objects.requireNonNull(typeParams);
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getModule() {
        return module;
    }
    
    public String getName() {
        return name;
    }
    
    public List<TypeTag> getTypeParams() {
        return typeParams;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(address).append("::").append(module).append("::").append(name);
        if (!typeParams.isEmpty()) {
            sb.append("<");
            for (int i = 0; i < typeParams.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(typeParams.get(i));
            }
            sb.append(">");
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TypeTagStructTag structTag = (TypeTagStructTag) obj;
        return Objects.equals(address, structTag.address) &&
               Objects.equals(module, structTag.module) &&
               Objects.equals(name, structTag.name) &&
               Objects.equals(typeParams, structTag.typeParams);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(address, module, name, typeParams);
    }
} 