package io.mangonet.mgo.model.object.kind.change;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.object.kind.ObjectChange;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Published implements ObjectChange {

    private String digest;

    private List<String> modules;

    @JsonProperty("packageId")
    private String packageId;

    private final String type = "published";

    private Long version;

    @Override
    public String getObjectId() {
        return packageId; // For published type, packageId is equivalent to objectId
    }
}
