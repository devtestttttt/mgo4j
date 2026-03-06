package io.mangonet.mgo.model.transaction.kind.epoch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StringEndOfEpoch extends MgoEndOfEpochTransactionKind {
    private String value;

    public StringEndOfEpoch(String value) {
        this.value = value;
        this.type = "StringType";
    }

    public static StringEndOfEpoch of(String type) {
        return new StringEndOfEpoch(type);
    }
}
