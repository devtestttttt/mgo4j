package io.mangonet.mgo.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MoveModuleFilter {

    private String module;

    @JsonProperty("package")
    private String packageId;

    public MoveModuleFilter(String module, String packageId) {
        this.module = module;
        this.packageId = packageId;
    }

}
