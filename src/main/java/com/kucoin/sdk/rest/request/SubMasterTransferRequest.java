package com.kucoin.sdk.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Reeta on 2019-05-20
 */
@Data
@AllArgsConstructor
public class SubMasterTransferRequest {

    private String clientOid;

    private String currency;

    private BigDecimal amount;

    private String direction;

    private String subUserId;

    private String subAccountType;

}
