package io.mangonet.mgo.model.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageForCoinAndString {

    private List<Coin> data;

    private Boolean hasNextPage;

    private String nextCursor;
}
