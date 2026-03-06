package io.mangonet.mgo.model.mgo.kind.call.enums;

import lombok.Getter;

@Getter
public enum ObjectTypeEnum {

    IMM_OR_OWNED_OBJECT("immOrOwnedObject"),
    RECEIVING("receiving"),
    SHARED_OBJECT("sharedObject"),
    U8("u8"),
    U64("u64"),
    U128("u128"),
    BOOL("bool"),
    STRING("string"),
    ADDRESS("address"),
    VECTOR_U8("vector<u8>"),
    VECTOR_U128("vector<u128>"),
    VECTOR_ADDRESS("vector<address>"),
    ;

    ObjectTypeEnum(String objectType) {
        this.objectType = objectType;
    }

    private final String objectType;
    
}
