/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MarginAccountResponse {

    private BigDecimal debtRatio;

    private List<MarginAccount> accounts;
}
