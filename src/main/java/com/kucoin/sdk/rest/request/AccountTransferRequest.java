/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tao.mao on 2018/9/3.
 * 用户账户内部转账
 */
@Data
@AllArgsConstructor
public class AccountTransferRequest implements Serializable {

    private static final long serialVersionUID = 9068593468335839904L;

    private String clientOid;

    private String payAccountId;

    private BigDecimal amount;
   
    private String recAccountId;

}
