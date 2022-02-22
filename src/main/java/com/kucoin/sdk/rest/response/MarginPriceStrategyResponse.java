package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author daz.deng
 * @description:
 * @date 2022/2/22 10:07
 */
@Data
public class MarginPriceStrategyResponse {

    private String currency;

    private BigDecimal borrowMaxAmount;

    private BigDecimal buyMaxAmount;

    private int precision;
}
