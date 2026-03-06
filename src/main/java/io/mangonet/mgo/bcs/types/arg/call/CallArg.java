package io.mangonet.mgo.bcs.types.arg.call;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class CallArg {
    
    public static boolean isPureArg(CallArg arg) {
        return arg instanceof CallArgPure;
    }
} 