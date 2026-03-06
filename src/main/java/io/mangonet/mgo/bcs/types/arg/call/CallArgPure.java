package io.mangonet.mgo.bcs.types.arg.call;

import io.mangonet.mgo.bcs.PureBcs;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CallArgPure extends CallArg {

    public CallArgPure(Object arg, PureBcs.BasePureType basePureType) {
        this.arg = arg;
        this.basePureType = basePureType;
    }

    public CallArgPure(byte[] rawBytes) {
        this.rawBytes = rawBytes;
    }

    private byte[] rawBytes;

    private Object arg;

    private PureBcs.BasePureType basePureType;
}