package io.mangonet.mgo.model.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain=true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MgoObjectDataFilter {

    @JsonProperty("MatchAll")
    private List<MgoObjectDataFilter> matchAll;

    @JsonProperty("MatchAny")
    private List<MgoObjectDataFilter> matchAny;

    @JsonProperty("MatchNone")
    private List<MgoObjectDataFilter> matchNone;

    @JsonProperty("Package")
    private String packageFilter;

    @JsonProperty("MoveModule")
    private MoveModuleFilter moveModuleFilter;

    @JsonProperty("StructType")
    private String structTypeFilter;

    @JsonProperty("AddressOwner")
    private String addressOwnerFilter;

    @JsonProperty("ObjectOwner")
    private String objectOwnerFilter;

    @JsonProperty("ObjectId")
    private String objectIdFilter;

    @JsonProperty("ObjectIds")
    private List<String> objectIdsFilter;

    @JsonProperty("Version")
    private Long versionFilter;

}
