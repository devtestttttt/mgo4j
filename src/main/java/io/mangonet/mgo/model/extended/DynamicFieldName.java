package io.mangonet.mgo.model.extended;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DynamicFieldName {

    private String type;

    private String value;

}
