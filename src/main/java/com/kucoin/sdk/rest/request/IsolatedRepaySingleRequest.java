package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName IsolatedRepaySingleRequest.java
 * @Description
 * @createTime 2023/05/17日 14:39:00
 */
@Data
public class IsolatedRepaySingleRequest {
    private String symbol;

    private String currency;

    private BigDecimal size;

    private String loanId;
}
