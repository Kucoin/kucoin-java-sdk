package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class RepayV3Response {

    private String orderNo;

    private BigDecimal actualSize;

}
