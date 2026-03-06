package io.mangonet.mgo.protocol.http.request;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UnsafePayAllMgo {

    private String signer;

    private List<String> inputCoins;

    private String recipient;

    private BigInteger gasBudget;
}
