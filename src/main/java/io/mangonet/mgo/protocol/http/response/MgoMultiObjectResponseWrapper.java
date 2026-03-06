package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.object.MgoObjectResponse;

import java.util.List;

public class MgoMultiObjectResponseWrapper extends Response<List<MgoObjectResponse>> {

    @Override
    public void setResult(List<MgoObjectResponse> result) {
        super.setResult(result);
    }

    @Override
    public List<MgoObjectResponse> getResult() {
        return super.getResult();
    }

}
