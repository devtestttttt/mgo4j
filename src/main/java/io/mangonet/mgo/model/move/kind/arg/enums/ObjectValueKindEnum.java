package io.mangonet.mgo.model.move.kind.arg.enums;

import lombok.Getter;

@Getter
public enum ObjectValueKindEnum {

    BY_IMMUTABLE_REFERENCE("ByImmutableReference"),
    BY_MUTABLE_REFERENCE("ByMutableReference"),
    BY_VALUE("ByValue"),
    ;

    ObjectValueKindEnum(String object) {
        this.object = object;
    }

    private final String object;
}
