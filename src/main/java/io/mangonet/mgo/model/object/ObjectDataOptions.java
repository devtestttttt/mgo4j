package io.mangonet.mgo.model.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectDataOptions {

    private Boolean showBcs;

    private Boolean showContent;

    private Boolean showDisplay;

    private Boolean showOwner;

    private Boolean showPreviousTransaction;

    private Boolean showStorageRebate;

    private Boolean showType;

    public static ObjectDataOptions allTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(true);
        options.setShowContent(true);
        options.setShowDisplay(true);
        options.setShowOwner(true);
        options.setShowPreviousTransaction(true);
        options.setShowStorageRebate(true);
        options.setShowType(true);
        return options;
    }

    public static ObjectDataOptions allFalse() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(false);
        options.setShowDisplay(false);
        options.setShowOwner(false);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(false);
        return options;
    }

    public static ObjectDataOptions contentAndBcsFalse() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(false);
        options.setShowDisplay(true);
        options.setShowOwner(true);
        options.setShowPreviousTransaction(true);
        options.setShowStorageRebate(true);
        options.setShowType(true);
        return options;
    }

    public static ObjectDataOptions contentTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(true);
        options.setShowDisplay(false);
        options.setShowOwner(false);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(false);
        return options;
    }

    public static ObjectDataOptions contentAndTypeTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(true);
        options.setShowDisplay(false);
        options.setShowOwner(false);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(true);
        return options;
    }

    public static ObjectDataOptions ownerTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(false);
        options.setShowDisplay(false);
        options.setShowOwner(true);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(false);
        return options;
    }

    public static ObjectDataOptions typeTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(false);
        options.setShowDisplay(false);
        options.setShowOwner(false);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(true);
        return options;
    }

    public static ObjectDataOptions ownerAndTypeTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(false);
        options.setShowDisplay(false);
        options.setShowOwner(true);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(true);
        return options;
    }

    public static ObjectDataOptions ownerAndTypeAndContentTrue() {
        ObjectDataOptions options = new ObjectDataOptions();
        options.setShowBcs(false);
        options.setShowContent(true);
        options.setShowDisplay(false);
        options.setShowOwner(true);
        options.setShowPreviousTransaction(false);
        options.setShowStorageRebate(false);
        options.setShowType(true);
        return options;
    }
}
