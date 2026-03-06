package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.transaction.TransactionBlockBytes;

public class TransactionBlockBytesWrapper extends Response<TransactionBlockBytes> {

    @Override
    public void setResult(TransactionBlockBytes result) {
        super.setResult(result);
    }

    @Override
    public TransactionBlockBytes getResult() {
        return super.getResult();
    }
}
