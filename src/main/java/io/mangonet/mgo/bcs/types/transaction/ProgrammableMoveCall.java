package io.mangonet.mgo.bcs.types.transaction;

import io.mangonet.mgo.bcs.types.tag.TypeTag;

import java.util.List;
import java.util.Objects;

public class ProgrammableMoveCall {
    
    private final String packageId;
    private final String module;
    private final String function;
    private final List<TypeTag> typeArguments;
    private final List<Argument> arguments;
    
    public ProgrammableMoveCall(String packageId, String module, String function, 
                               List<TypeTag> typeArguments, List<Argument> arguments) {
        this.packageId = Objects.requireNonNull(packageId);
        this.module = Objects.requireNonNull(module);
        this.function = Objects.requireNonNull(function);
        this.typeArguments = Objects.requireNonNull(typeArguments);
        this.arguments = Objects.requireNonNull(arguments);
    }
    
    public String getPackageId() {
        return packageId;
    }
    
    public String getModule() {
        return module;
    }
    
    public String getFunction() {
        return function;
    }
    
    public List<TypeTag> getTypeArguments() {
        return typeArguments;
    }
    
    public List<Argument> getArguments() {
        return arguments;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgrammableMoveCall that = (ProgrammableMoveCall) obj;
        return Objects.equals(packageId, that.packageId) &&
               Objects.equals(module, that.module) &&
               Objects.equals(function, that.function) &&
               Objects.equals(typeArguments, that.typeArguments) &&
               Objects.equals(arguments, that.arguments);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(packageId, module, function, typeArguments, arguments);
    }
    
    @Override
    public String toString() {
        return "ProgrammableMoveCall{" +
               "packageId='" + packageId + '\'' +
               ", module='" + module + '\'' +
               ", function='" + function + '\'' +
               ", typeArguments=" + typeArguments +
               ", arguments=" + arguments +
               '}';
    }
} 