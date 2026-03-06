package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.move.MgoMoveNormalizedStruct;

public class MgoMoveNormalizedStructWrapper extends Response<MgoMoveNormalizedStruct> {

    @Override
    public void setResult(MgoMoveNormalizedStruct result) {
        super.setResult(result);
    }

    @Override
    public MgoMoveNormalizedStruct getResult() {
        return super.getResult();
    }

}
