package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Reeta on 2019-05-22
 */
@Data
public class SnapshotResponse {

    private String symbol;

    private String symbolCode;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal open;

    private BigDecimal lastTradedPrice;

    private BigDecimal changeRate;

    private BigDecimal changePrice;

    private BigDecimal vol;

    private BigDecimal volValue;

    private BigDecimal close;

    private String baseCurrency;

    private String quoteCurrency;

    private Boolean trading;

    private Integer sort;

    private Integer board;

    private Integer mark;

    private String market;

    private long datetime;

}
