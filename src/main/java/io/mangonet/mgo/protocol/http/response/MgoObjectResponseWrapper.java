package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.object.MgoObjectResponse;

public class MgoObjectResponseWrapper extends Response<MgoObjectResponse> {

    @Override
    public void setResult(MgoObjectResponse result) {
        super.setResult(result);
    }

    @Override
    public MgoObjectResponse getResult() {
        return super.getResult();
    }

}
