/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDetailResponse extends CurrencyResponse {
    private BigDecimal withdrawalMinSize;

    private BigDecimal withdrawalMinFee;

    private Boolean isWithdrawEnabled;

    private Boolean isDepositEnabled;
}
