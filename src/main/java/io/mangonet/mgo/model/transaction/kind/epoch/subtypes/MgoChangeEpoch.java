package io.mangonet.mgo.model.transaction.kind.epoch.subtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
public class MgoChangeEpoch {
    @JsonProperty("computation_charge")
    private BigInteger computationCharge; // BigInt_for_uint64

    private BigInteger epoch; // BigInt_for_uint64

    @JsonProperty("epoch_start_timestamp_ms")
    private BigInteger epochStartTimestampMs; // BigInt_for_uint64

    @JsonProperty("storage_charge")
    private BigInteger storageCharge; // BigInt_for_uint64

    @JsonProperty("storage_rebate")
    private BigInteger storageRebate; // BigInt_for_uint64
}