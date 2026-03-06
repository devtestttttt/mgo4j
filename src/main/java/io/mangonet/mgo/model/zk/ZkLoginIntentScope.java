package io.mangonet.mgo.model.zk;

import lombok.Getter;

@Getter
public enum ZkLoginIntentScope {

    TRANSACTION_DATA("TransactionData"),
    PERSONAL_MESSAGE("PersonalMessage"),
    ;

    ZkLoginIntentScope(String scope) {
        this.scope = scope;
    }

    private final String scope;

}
