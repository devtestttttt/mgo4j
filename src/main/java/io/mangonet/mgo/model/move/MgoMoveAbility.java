package io.mangonet.mgo.model.move;

import lombok.Getter;

@Getter
public enum MgoMoveAbility {

    COPY("Copy"),
    DROP("Drop"),
    STORE("Store"),
    KEY("Key"),
    ;

    MgoMoveAbility(String value) {
        this.value = value;
    }

    private final String value;
}
