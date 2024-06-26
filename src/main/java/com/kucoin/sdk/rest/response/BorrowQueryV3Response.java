package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class BorrowQueryV3Response {

    private String orderNo;

    private String symbol;

    private String currency;

    private BigDecimal size;

    private BigDecimal actualSize;

    private String status;

    private Long createdTime;

}