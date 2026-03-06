package io.mangonet.mgo.model.transaction.kind.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferObjectParams {

    private String objectId;

    private String recipient;

}
