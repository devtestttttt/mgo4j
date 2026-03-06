package io.mangonet.mgo.model.mgo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoJwkId {

    @JsonProperty("iss")
    private String iss;

    @JsonProperty("kid")
    private String kid;
}

