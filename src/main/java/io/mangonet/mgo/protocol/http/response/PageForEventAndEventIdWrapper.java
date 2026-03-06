package io.mangonet.mgo.protocol.http.response;


import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.model.event.PageForEventAndEventId;

public class PageForEventAndEventIdWrapper extends Response<PageForEventAndEventId> {

    @Override
    public void setResult(PageForEventAndEventId result) {
        super.setResult(result);
    }

    @Override
    public PageForEventAndEventId getResult() {
        return super.getResult();
    }

}

