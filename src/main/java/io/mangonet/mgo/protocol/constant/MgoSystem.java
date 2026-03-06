package io.mangonet.mgo.protocol.constant;

import io.mangonet.mgo.util.ObjectIdUtil;

import java.math.BigInteger;

public interface MgoSystem {

    int MGO_DECIMALS = 9;
    BigInteger MIST_PER_MGO = BigInteger.valueOf(1000000000L);

    String MGO = "0x2::coin::Coin<0x2::mgo::MGO>";

    String MOVE_STDLIB_ADDRESS = "0x1";
    String MGO_FRAMEWORK_ADDRESS = "0x2";
    String MGO_SYSTEM_ADDRESS = "0x3";
    String MGO_CLOCK_OBJECT_ID = ObjectIdUtil.normalizeMgoAddress("0x6");
    String MGO_SYSTEM_MODULE_NAME = "mgo_system";
    String MGO_TYPE_ARG = MGO_FRAMEWORK_ADDRESS + "::mgo::MGO";
    String MGO_TYPE = ObjectIdUtil.normalizeMgoAddress(MGO_FRAMEWORK_ADDRESS) + "::mgo::MGO";
    String MGO_SYSTEM_STATE_OBJECT_ID = ObjectIdUtil.normalizeMgoAddress("0x5");

}
