package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.governance.MgoSystemStateSummary;

public class MgoSystemStateWrapper extends Response<MgoSystemStateSummary> {

    @Override
    public void setResult(MgoSystemStateSummary result) {
        super.setResult(result);
    }

    @Override
    public MgoSystemStateSummary getResult() {
        return super.getResult();
    }
}
