package io.mangonet.mgo.protocol.http.request;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UnsafePayMgo {

    private String signer;

    private List<String> inputCoins;

    private List<String> recipients;

    private List<BigInteger> amounts;

    private BigInteger gasBudget;
}
