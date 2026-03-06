package io.mangonet.mgo.model.transaction.kind.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.TransactionBlockKind;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeEpoch extends TransactionBlockKind {

    @JsonProperty("computation_charge")
    private BigInteger computationCharge;

    private BigInteger epoch;

    @JsonProperty("epoch_start_timestamp_ms")
    private BigInteger epochStartTimestampMs;

    @JsonProperty("storage_charge")
    private BigInteger storageCharge;

    @JsonProperty("storage_rebate")
    private BigInteger storageRebate;

    public ChangeEpoch() {
        this.kind = "ChangeEpoch";
    }
}
