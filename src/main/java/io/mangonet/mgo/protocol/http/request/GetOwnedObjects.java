package io.mangonet.mgo.protocol.http.request;

import io.mangonet.mgo.model.object.ObjectResponseQuery;
import lombok.Data;

@Data
public class GetOwnedObjects {

    private String address;

    private ObjectResponseQuery query;

    private String cursor;

    private Long limit;

}
