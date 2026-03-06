package io.mangonet.mgo.model.transaction.kind.operate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.kind.MgoArgument;
import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitCoins extends MgoTransaction {

    @JsonProperty("SplitCoins")
    private SplitCoinsData splitCoins;  // [MgoArgument, MgoArgument[]]

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SplitCoinsData {
        private MgoArgument coin;
        private List<MgoArgument> amounts;
    }
}
