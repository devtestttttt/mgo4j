package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.move.MgoMoveNormalizedFunction;

public class MgoMoveNormalizedFunctionWrapper extends Response<MgoMoveNormalizedFunction> {

    @Override
    public void setResult(MgoMoveNormalizedFunction result) {
        super.setResult(result);
    }

    @Override
    public MgoMoveNormalizedFunction getResult() {
        return super.getResult();
    }

}
