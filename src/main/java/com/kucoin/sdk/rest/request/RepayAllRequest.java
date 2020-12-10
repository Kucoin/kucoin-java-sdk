/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RepayAllRequest {

    private String currency;

    private BigDecimal size;

    private RepaySeqStrategy sequence;
}
