package io.mangonet.mgo.model.object;

import io.mangonet.mgo.model.filter.MgoObjectDataFilter;
import lombok.Data;

@Data
public class ObjectResponseQuery {

    private MgoObjectDataFilter filter;

    private ObjectDataOptions options = ObjectDataOptions.allTrue();

}
