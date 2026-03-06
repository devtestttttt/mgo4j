package io.mangonet.mgo.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.ObjectRef;
import io.mangonet.mgo.model.object.kind.InputObjectKind;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionBlockBytes {

    private String txBytes;

    private List<ObjectRef> gas;

    private List<InputObjectKind> inputObjects;

}
