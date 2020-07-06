package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderChangeEvent {

    private String orderId;

    private String symbol;

    private String type;

    private String status;

    private BigDecimal matchPrice;

    private BigDecimal matchSize;

    private String orderType;

    private String side;

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal filledSize;

    private BigDecimal oldSize;

    private String tradeId;

    private String clientOid;

    private Long orderTime;

    private String liquidity;

    private BigDecimal remainSize;

    private BigDecimal remainFunds;

    private long ts;

    private BigDecimal funds;
}
