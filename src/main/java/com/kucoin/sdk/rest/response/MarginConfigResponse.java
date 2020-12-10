/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MarginConfigResponse {

    private List<String> currencyList;

    private Integer maxLeverage;

    private BigDecimal warningDebtRatio;

    private BigDecimal liqDebtRatio;
}
