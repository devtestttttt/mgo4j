package io.mangonet.mgo.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.kind.ObjectResponseError;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplayFieldsResponse {

    private Map<String, String> data;

    private ObjectResponseError error;

}
