package io.mangonet.mgo.model.transaction.kind.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.mangonet.mgo.model.transaction.kind.RPCTransactionRequestParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveCallParamsWrapper extends RPCTransactionRequestParams {

    private MoveCallParams moveCallRequestParams;

}
