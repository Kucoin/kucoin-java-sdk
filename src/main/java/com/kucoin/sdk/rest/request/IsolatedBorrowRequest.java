package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName IsolatedBorrowRequest.java
 * @Description
 * @createTime 2023/05/17日 11:45:00
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
