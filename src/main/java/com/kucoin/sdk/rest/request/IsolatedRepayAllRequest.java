package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class IsolatedRepayAllRequest {
    private String symbol;

    private String currency;

    private BigDecimal size;

    private String seqStrategy;
}
