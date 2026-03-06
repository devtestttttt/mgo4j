package io.mangonet.mgo.model.move.kind.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MoveValue;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveVariant {

    private String type;

    private String variant;

    private Map<String, MoveValue> fields = new LinkedHashMap<>();

}
