package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.move.MgoMoveNormalizedModule;

import java.util.Map;

public class MgoMoveNormalizedModuleByPackageWrapper extends Response<Map<String, MgoMoveNormalizedModule>> {

    @Override
    public void setResult(Map<String, MgoMoveNormalizedModule> result) {
        super.setResult(result);
    }

    @Override
    public Map<String, MgoMoveNormalizedModule> getResult() {
        return super.getResult();
    }

}
