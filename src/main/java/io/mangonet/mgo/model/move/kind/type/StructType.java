package io.mangonet.mgo.model.move.kind.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.move.kind.MgoMoveNormalizedType;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StructType extends MgoMoveNormalizedType {

    private String address;

    private String module;

    private String name;

    private List<MgoMoveNormalizedType> typeArguments;

}
