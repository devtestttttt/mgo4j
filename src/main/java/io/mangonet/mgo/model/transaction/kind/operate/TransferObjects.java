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
public class TransferObjects extends MgoTransaction {

    @JsonProperty("TransferObjects")
    private TransferObjectsData transferObjects;    // [MgoArgument[], MgoArgument]

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TransferObjectsData {
        private List<MgoArgument> objects;
        private MgoArgument address;
    }
}
