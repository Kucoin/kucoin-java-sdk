/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by tao.mao on 2018/11/15.
 */
@Data
public class AccountBalanceResponse {

    private String currency;

    private BigDecimal balance;

    private BigDecimal available;

    private BigDecimal holds;

}
