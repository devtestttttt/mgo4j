package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.transaction.MgoTransactionBlockResponse;

public class MgoTransactionBlockResponseWrapper extends Response<MgoTransactionBlockResponse> {

    @Override
    public void setResult(MgoTransactionBlockResponse result) {
        super.setResult(result);
    }

    @Override
    public MgoTransactionBlockResponse getResult() {
        return super.getResult();
    }

}
