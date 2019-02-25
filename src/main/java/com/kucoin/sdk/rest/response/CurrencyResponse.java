/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {
    private String fullName;

    private String currency;

    private String name;

    private int precision;

    private BigDecimal withdrawalMinFee;

    private BigDecimal withdrawalMinSize;

    private boolean isIsWithdrawEnabled;

    private boolean isIsDepositEnabled;
}
