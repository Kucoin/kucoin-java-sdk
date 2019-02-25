/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawQuotaResponse {

    private String currency;

    private BigDecimal availableAmount;

    private BigDecimal remainAmount;

    private BigDecimal withdrawMinSize;

    private BigDecimal limitBTCAmount;

    private BigDecimal limitAmount;

    private BigDecimal innerWithdrawMinFee;

    private BigDecimal usedBTCAmount;

    private Boolean isWithdrawEnabled;

    private BigDecimal withdrawMinFee;

    private Integer precision;

}
