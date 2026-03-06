package io.mangonet.mgo.model.move.kind.type.enums;

import lombok.Getter;

@Getter
public enum PrimitiveEnum {

    BOOL("Bool"),
    U8("U8"),
    U16("U16"),
    U32("U32"),
    U64("U64"),
    U128("U128"),
    U256("U256"),
    ADDRESS("Address"),
    SIGNER("Signer"),
    ;

    PrimitiveEnum(String value) {
        this.value = value;
    }

    private final String value;

    public static PrimitiveEnum find(String value) {
        for (PrimitiveEnum e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
