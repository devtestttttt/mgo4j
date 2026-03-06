package io.mangonet.mgo.model.gas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.object.ObjectRef;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GasData {

    private BigInteger budget;

    private String owner;

    private List<ObjectRef> payment;

    private BigInteger price;

}
