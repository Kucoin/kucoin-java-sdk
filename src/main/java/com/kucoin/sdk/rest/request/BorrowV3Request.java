/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
@Builder
public class BorrowV3Request {

    /**
     * true-isolated, false-cross; default is false
     */
    private boolean isIsolated;

    /**
     * true: high frequency borrowing, false: low frequency borrowing; default false
     */
    private boolean isHf;

    /**
     * Trading pair, mandatory for isolated margin account
     */
    private String symbol;

    /**
     * Borrowed currency
     */
    private String currency;

    /**
     * Borrowed amount
     */
    private BigDecimal size;

    /**
     * timeInForce: IOC, FOK
     * @see com.kucoin.sdk.enums.TimeInForceEnum
     */
    private String timeInForce;

}
