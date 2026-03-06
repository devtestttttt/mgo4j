package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.zk.ZkLoginVerifyResult;

public class ZkLoginVerifyResultWrapper extends Response<ZkLoginVerifyResult> {

    @Override
    public void setResult(ZkLoginVerifyResult result) {
        super.setResult(result);
    }

    @Override
    public ZkLoginVerifyResult getResult() {
        return super.getResult();
    }

}
