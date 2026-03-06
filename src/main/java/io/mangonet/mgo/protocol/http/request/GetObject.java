package io.mangonet.mgo.protocol.http.request;

import io.mangonet.mgo.model.object.ObjectDataOptions;
import lombok.Data;

@Data
public class GetObject {

    private String objectId;

    private ObjectDataOptions options;

}
