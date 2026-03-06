package io.mangonet.mgo.bcs.types.arg.call;

import io.mangonet.mgo.bcs.types.arg.object.ObjectArg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallArgObjectArg extends CallArg {

    private ObjectArg objectArg;
} 