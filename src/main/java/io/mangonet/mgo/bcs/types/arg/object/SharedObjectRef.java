package io.mangonet.mgo.bcs.types.arg.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedObjectRef {

    private String objectId;
    private long initialSharedVersion;
    private boolean mutable;

} 