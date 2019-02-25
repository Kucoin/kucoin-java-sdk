/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerResponse {

    private String sequence;

    private BigDecimal bestAsk;

    private BigDecimal bestBid;

    private BigDecimal size;

    private BigDecimal price;

    private BigDecimal bestAskSize;

    private BigDecimal bestBidSize;

    private long time;

}
