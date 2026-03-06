package io.mangonet.mgo.model.object.kind.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.kind.ObjectResponseError;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deleted implements ObjectResponseError {

    private final String code = "deleted";

    private String digest;

    private String objectId;

    private String version;
}
