/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarginOrderCreateResponse {

    private String orderId;

    private String loanApplyId;

    private BigDecimal borrowSize;

}
