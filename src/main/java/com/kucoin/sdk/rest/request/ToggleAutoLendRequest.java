/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ToggleAutoLendRequest {

    private String currency;

    private Boolean isEnable;

    private BigDecimal retainSize;

    private BigDecimal dailyIntRate;

    private Integer term;
}
