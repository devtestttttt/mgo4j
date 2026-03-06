package io.mangonet.mgo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mangonet.mgo.protocol.MgoService;
import io.mangonet.mgo.protocol.core.DefaultIdProvider;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Request<S, T extends Response> {

    private String jsonrpc = "2.0";
    private String method;
    private List<S> params;
    private long id;

    private MgoService mgoService;

    // Unfortunately require an instance of the type too, see
    // http://stackoverflow.com/a/3437930/3211687
    private Class<T> responseType;

    public Request() {}

    public Request(String method, List<S> params, MgoService mgoService, Class<T> type) {
        this.method = method;
        this.params = params;
        this.id = DefaultIdProvider.getNextId();
        this.mgoService = mgoService;
        this.responseType = type;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<S> getParams() {
        return params;
    }

    public void setParams(List<S> params) {
        this.params = params;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public Class<T> getResponseType() {
        return responseType;
    }

    public T send() throws IOException {
        return mgoService.send(this, responseType);
    }

    public CompletableFuture<T> sendAsync() {
        return mgoService.sendAsync(this, responseType);
    }

}
