/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Created by tao.mao on 2018/11/15.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHoldsResponse {

    private String currency;

    private BigDecimal holdAmount;

    private String bizType;

    private String orderId;

    private String createdAt;

    private String updatedAt;
}
