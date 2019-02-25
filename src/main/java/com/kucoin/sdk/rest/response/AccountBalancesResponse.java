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
public class AccountBalancesResponse {

    private String id;

    private String currency;

    private String type;

    private BigDecimal balance;

    private BigDecimal available;

    private BigDecimal holds;

}
