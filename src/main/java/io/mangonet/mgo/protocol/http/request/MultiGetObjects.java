package io.mangonet.mgo.protocol.http.request;

import io.mangonet.mgo.model.object.ObjectDataOptions;
import lombok.Data;

import java.util.List;

@Data
public class MultiGetObjects {

    private List<String> objectIds;

    private ObjectDataOptions options;
}
