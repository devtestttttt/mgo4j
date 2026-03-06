package io.mangonet.mgo.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageForMgoObjectResponseAndObjectId {

    private List<MgoObjectResponse> data;

    private Boolean hasNextPage;

    private String nextCursor;

}
