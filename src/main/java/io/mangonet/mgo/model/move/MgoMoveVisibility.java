package io.mangonet.mgo.model.move;

import lombok.Getter;

@Getter
public enum MgoMoveVisibility {

    PRIVATE("Private"),
    PUBLIC("Public"),
    FRIEND("Friend"),
    ;

    MgoMoveVisibility(String value) {
        this.value = value;
    }

    private final String value;

    public static MgoMoveVisibility findByValue(String value) {
        for (MgoMoveVisibility visibility : values()) {
            if (visibility.value.equals(value)) {
                return visibility;
            }
        }
        return null;
    }
}
