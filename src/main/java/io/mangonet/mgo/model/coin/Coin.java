package io.mangonet.mgo.model.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {

    private BigInteger balance;

    private String coinObjectId;

    private String coinType;

    private String digest;

    private String previousTransaction;

    private Long version;

}
