package io.mangonet.mgo.model.read;

import io.mangonet.mgo.model.Response;
import io.mangonet.mgo.util.Numeric;

import java.math.BigInteger;

public class ChainIdentifier extends Response<String> {

    public BigInteger getMgoChainIdentifier() {
        return Numeric.decodeQuantity(getResult());
    }
}
