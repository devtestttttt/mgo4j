package io.mangonet.mgo.bcs.types.arg.object;

import io.mangonet.mgo.bcs.types.gas.MgoObjectRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectArgReceiving extends ObjectArg {

    private MgoObjectRef objectRef;
} 