package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.coin.Balance;

public class BalanceWrapper extends Response<Balance> {

    @Override
    public void setResult(Balance result) {
        super.setResult(result);
    }

    @Override
    public Balance getResult() {
        return super.getResult();
    }

}
