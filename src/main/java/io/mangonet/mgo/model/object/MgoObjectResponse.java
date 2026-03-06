package io.mangonet.mgo.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.kind.ObjectResponseError;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoObjectResponse {

    private ObjectData data;

    private ObjectResponseError error;

}
