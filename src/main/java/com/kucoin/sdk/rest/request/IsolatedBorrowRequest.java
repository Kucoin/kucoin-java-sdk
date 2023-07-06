package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class IsolatedBorrowRequest {
    private String symbol;

    private String currency;

    private BigDecimal size;

    private String borrowStrategy;

    private BigDecimal maxRate;

    private String period;
}
