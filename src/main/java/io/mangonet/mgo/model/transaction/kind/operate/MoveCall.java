package io.mangonet.mgo.model.transaction.kind.operate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mangonet.mgo.model.mgo.MoveCallMgoTransaction;
import io.mangonet.mgo.model.transaction.kind.MgoTransaction;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveCall extends MgoTransaction {

    @JsonProperty("MoveCall")
    private MoveCallMgoTransaction moveCall;

}
