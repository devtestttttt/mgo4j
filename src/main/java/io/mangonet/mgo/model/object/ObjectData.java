package io.mangonet.mgo.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.Data;
import io.mangonet.mgo.model.move.kind.RawData;
import io.mangonet.mgo.model.object.kind.Owner;

import java.math.BigInteger;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectData {

    private String objectId;

    private BigInteger version;

    private String digest;

    private String type;

    private Owner owner;

    private String previousTransaction;

    private String storageRebate;

    private Data content;

    private RawData bcs;

    private DisplayFieldsResponse display;

}
