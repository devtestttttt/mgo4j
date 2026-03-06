package io.mangonet.mgo.protocol.http.response;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.move.kind.MoveFunctionArgType;

import java.util.List;

public class MoveFunctionArgTypesWrapper extends Response<List<MoveFunctionArgType>> {

    @Override
    public void setResult(List<MoveFunctionArgType> result) {
        super.setResult(result);
    }

    @Override
    public List<MoveFunctionArgType> getResult() {
        return super.getResult();
    }

}
