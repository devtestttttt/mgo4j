package io.mangonet.mgo.model.mgo.kind.call.enums;

import lombok.Getter;

@Getter
public enum TypeEnum {

    OBJECT("object"),
    PURE("pure"),
    ;

    TypeEnum(String type) {
        this.type = type;
    }

    private final String type;
}
