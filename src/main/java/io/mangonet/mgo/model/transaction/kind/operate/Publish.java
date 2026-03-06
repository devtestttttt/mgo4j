package io.mangonet.mgo.model.transaction.kind.operate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Publish extends MgoTransaction {

    @JsonProperty("Publish")
    private List<String> publish;   // string[]
}
