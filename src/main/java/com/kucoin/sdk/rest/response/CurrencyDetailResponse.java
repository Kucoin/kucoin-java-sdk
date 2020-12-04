/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDetailResponse extends CurrencyResponse {

    private BigDecimal withdrawalMinSize;

    private BigDecimal withdrawalMinFee;

    @JsonProperty("isWithdrawEnabled")
    private Boolean isWithdrawEnabled;

    @JsonProperty("isDepositEnabled")
    private Boolean isDepositEnabled;

}
