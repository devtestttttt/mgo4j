package io.mangonet.mgo.model.move.kind.rawdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.RawData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveObjectRaw implements RawData {

    private final String dataType = "moveObject";

    private String bcsBytes; // Base64encode

    private boolean hasPublicTransfer;

    private String type;

    private String version;
}
