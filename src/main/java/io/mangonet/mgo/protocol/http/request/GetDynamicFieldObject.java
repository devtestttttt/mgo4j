package io.mangonet.mgo.protocol.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.extended.DynamicFieldName;
import lombok.Data;

@Data
public class GetDynamicFieldObject {

    @JsonProperty("parent_object_id")
    private String parentObjectId;

    private DynamicFieldName name;

}
