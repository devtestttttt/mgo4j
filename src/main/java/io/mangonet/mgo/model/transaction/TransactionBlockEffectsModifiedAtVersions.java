package io.mangonet.mgo.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionBlockEffectsModifiedAtVersions {

    private String objectId;

    private Long sequenceNumber;
}
