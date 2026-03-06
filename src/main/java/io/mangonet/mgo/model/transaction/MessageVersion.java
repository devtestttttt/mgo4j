package io.mangonet.mgo.model.transaction;

import lombok.Getter;

@Getter
public enum MessageVersion {

    V1("v1"),
            ;

    MessageVersion(String version) {
        this.version = version;
    }

    private final String version;
}
