package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/2/22.
 */
@Data
public class MarketTickerResponse {

    private String symbol;

    private String symbolName;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal changeRate;

    private BigDecimal changePrice;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal vol;

    private BigDecimal last;

    private BigDecimal volValue;

    private BigDecimal averagePrice;

    private BigDecimal takerFeeRate;

    private BigDecimal makerFeeRate;

    private BigDecimal takerCoefficient;

    private BigDecimal makerCoefficient;

}
