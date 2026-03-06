package io.mangonet.mgo.bcs.types.arg.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectArgSharedObject extends ObjectArg {

    private SharedObjectRef objectRef;
} 