package io.mangonet.mgo.bcs.types.effects;

public class ExecutionFailureStatus {
    private final String errorType;
    private final Object errorData;
    
    public ExecutionFailureStatus(String errorType, Object errorData) {
        this.errorType = errorType;
        this.errorData = errorData;
    }
    
    public String getErrorType() {
        return errorType;
    }
    
    public Object getErrorData() {
        return errorData;
    }
} 