package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.coin.PageForCoinAndString;

public class PageForCoinAndStringWrapper extends Response<PageForCoinAndString> {

    @Override
    public void setResult(PageForCoinAndString result) {
        super.setResult(result);
    }

    @Override
    public PageForCoinAndString getResult() {
        return super.getResult();
    }

}
