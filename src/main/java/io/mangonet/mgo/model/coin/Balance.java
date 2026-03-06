package io.mangonet.mgo.model.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {

    private Integer coinObjectCount;

    private String coinType;

    private LockedBalance lockedBalance;

    private BigInteger totalBalance;

}
