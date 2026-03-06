package io.mangonet.mgo.model.transaction;

import lombok.Getter;

@Getter
public enum ExecuteTransactionRequestType {

    WAIT_FOR_EFFECTS_CERT("WaitForEffectsCert"),

    WAIT_FOR_LOCAL_EXECUTION("WaitForLocalExecution");

    ExecuteTransactionRequestType(String type) {
        this.type = type;
    }

    private final String type;
}
