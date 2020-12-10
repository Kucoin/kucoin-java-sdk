/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by shenteng on 2019-10-15.
 */
@Data
public class BaseLoanItem {

    private String orderId;

    private String currency;

    private BigDecimal size;

    private BigDecimal filledSize;

    private BigDecimal dailyIntRate;

    private Integer term;

    private Date createdAt;
}
