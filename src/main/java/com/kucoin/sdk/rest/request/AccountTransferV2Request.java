package com.kucoin.sdk.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Reeta on 2019/9/26
 */
@Data
@AllArgsConstructor
public class AccountTransferV2Request implements Serializable {

    private static final long serialVersionUID = 9068593468335839904L;

    private String clientOid;

    private String currency;

    private String from;

    private String to;

    private BigDecimal amount;

}