package io.mangonet.mgo.model.gas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GasCostSummary {

    private BigInteger computationCost;

    private BigInteger nonRefundableStorageFee;

    private BigInteger storageCost;

    private BigInteger storageRebate;

}
