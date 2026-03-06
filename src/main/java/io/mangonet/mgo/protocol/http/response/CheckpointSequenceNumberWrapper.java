package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;

import java.math.BigInteger;

public class CheckpointSequenceNumberWrapper extends Response<String> {

    @Override
    public void setResult(String result) {
        super.setResult(result);
    }

    public BigInteger getCheckpointSequenceNumber() {
        return new BigInteger(getResult());
    }
}
