package io.mangonet.mgo.model.move.kind.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.Data;

import java.util.Map;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Package implements Data {

    private final String dataType = "package";

    private Map<String, Object> disassembled;
}
