package io.mangonet.mgo.model.move.kind.rawdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.TypeOrigin;
import io.mangonet.mgo.model.move.UpgradeInfo;
import io.mangonet.mgo.model.move.kind.RawData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageRaw implements RawData {

    private final String dataType = "package";

    private String id;

    private Map<String, UpgradeInfo> linkageTable;

    private Map<String, String> moduleMap; // Base64 encoded module content

    private List<TypeOrigin> typeOriginTable;

    private String version;
}
