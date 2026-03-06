package io.mangonet.mgo.model.move;

import lombok.Getter;

@Getter
public enum MgoTransactionBlockBuilderMode {

    COMMIT("Commit"),   // default, regular Mgo transaction committed on-chain
    DEV_INSPECT("DevInspect"),  // Allows calling any Move function with arbitrary values in simulated transaction.
    ;

    MgoTransactionBlockBuilderMode(String mode) {
        this.mode = mode;
    }

    private final String mode;
}
