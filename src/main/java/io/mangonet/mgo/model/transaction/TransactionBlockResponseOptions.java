package io.mangonet.mgo.model.transaction;

import lombok.Data;

@Data
public class TransactionBlockResponseOptions {

    private Boolean showBalanceChanges;

    private Boolean showEffects;

    private Boolean showEvents;

    private Boolean showInput;

    private Boolean showObjectChanges;

    private Boolean showRawEffects;

    private Boolean showRawInput;

    public static TransactionBlockResponseOptions allTrue() {
        TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
        options.setShowBalanceChanges(true);
        options.setShowEffects(true);
        options.setShowEvents(true);
        options.setShowInput(true);
        options.setShowObjectChanges(true);
        options.setShowRawEffects(true);
        options.setShowRawInput(true);
        return options;
    }

    public static TransactionBlockResponseOptions rawFalse() {
        TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
        options.setShowBalanceChanges(true);
        options.setShowEffects(true);
        options.setShowEvents(true);
        options.setShowInput(true);
        options.setShowObjectChanges(true);
        options.setShowRawEffects(false);
        options.setShowRawInput(false);
        return options;
    }

    public static TransactionBlockResponseOptions changesAndEffectsTrue() {
        TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
        options.setShowBalanceChanges(true);
        options.setShowEffects(true);
        options.setShowEvents(false);
        options.setShowInput(false);
        options.setShowObjectChanges(true);
        options.setShowRawEffects(false);
        options.setShowRawInput(false);
        return options;
    }

    public static TransactionBlockResponseOptions changesAndEventsAndEffectsTrue() {
        TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
        options.setShowBalanceChanges(true);
        options.setShowEffects(true);
        options.setShowEvents(true);
        options.setShowInput(false);
        options.setShowObjectChanges(true);
        options.setShowRawEffects(false);
        options.setShowRawInput(false);
        return options;
    }

}
