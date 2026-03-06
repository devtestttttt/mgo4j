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
public class Upgrade extends MgoTransaction {

    @JsonProperty("Upgrade")
    private UpgradeData upgrade;    // [string[], string, MgoArgument]

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UpgradeData {
        private List<String> packageBytes;
        private String packageId;
        private MgoArgument upgradeTicket;
    }
}
