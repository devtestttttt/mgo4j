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
public class MakeMoveVec extends MgoTransaction {

    @JsonProperty("MakeMoveVec")
    private MakeMoveVecData makeMoveVec;    // [string | null, MgoArgument[]]

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MakeMoveVecData {
        private String typeTag;
        private List<MgoArgument> elements;
    }
}
