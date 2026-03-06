package io.mangonet.mgo.model.object.kind.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.kind.ObjectResponseError;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicFieldNotFound implements ObjectResponseError {

    private final String code = "dynamicFieldNotFound";

    private String parentObjectId;
}

