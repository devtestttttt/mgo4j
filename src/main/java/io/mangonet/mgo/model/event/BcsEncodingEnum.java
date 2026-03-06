package io.mangonet.mgo.model.event;

import lombok.Getter;

@Getter
public enum BcsEncodingEnum {

    BASE58("base58"),
    BASE64("base64"),
    ;

    BcsEncodingEnum(String encode) {
        this.encode = encode;
    }

    private final String encode;
}
