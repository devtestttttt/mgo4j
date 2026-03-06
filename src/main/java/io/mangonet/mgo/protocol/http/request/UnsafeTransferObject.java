package io.mangonet.mgo.protocol.http.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UnsafeTransferObject {

    private String signer;

    private String objectId;

    private String gas;

    private BigInteger gasBudget;

    private String recipient;
}
