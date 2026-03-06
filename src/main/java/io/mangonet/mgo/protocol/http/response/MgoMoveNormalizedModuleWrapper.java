package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.move.MgoMoveNormalizedModule;

public class MgoMoveNormalizedModuleWrapper extends Response<MgoMoveNormalizedModule> {

    @Override
    public void setResult(MgoMoveNormalizedModule result) {
        super.setResult(result);
    }

    @Override
    public MgoMoveNormalizedModule getResult() {
        return super.getResult();
    }

}