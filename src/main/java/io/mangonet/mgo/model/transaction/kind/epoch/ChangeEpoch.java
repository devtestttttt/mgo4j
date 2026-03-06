package io.mangonet.mgo.model.transaction.kind.epoch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.MgoEndOfEpochTransactionKind;
import io.mangonet.mgo.model.transaction.kind.epoch.subtypes.MgoChangeEpoch;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeEpoch extends MgoEndOfEpochTransactionKind {

    @JsonProperty("ChangeEpoch")
    private MgoChangeEpoch changeEpoch;

    public ChangeEpoch() {
        this.type = "ChangeEpoch";
    }
}