package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Data
public class TradeHistoryResponse {

    private String sequence;

    private BigDecimal price;

    private BigDecimal size;

    private String side;

    private long time;

}
