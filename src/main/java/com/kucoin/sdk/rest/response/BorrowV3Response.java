/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class BorrowV3Response {

    /**
     * Borrow order number
     */
    private String orderNo;

    /**
     * Actual borrowed amount
     */
    private BigDecimal actualSize;

}
