package io.mangonet.mgo.protocol.http.request;

import io.mangonet.mgo.model.zk.ZkLoginIntentScope;
import lombok.Data;

@Data
public class VerifyZkLoginSignature {

    private String bytes;

    private String signature;

    private String intentScope;

    private String author;
}
