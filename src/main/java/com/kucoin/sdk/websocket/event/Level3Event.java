package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Level3Event {

    private long sequence;

    private long ts;

    private String symbol;

    private String side;

    private BigDecimal size;

    private BigDecimal remainSize;

    private String orderId;

    private String makerOrderId;

    private String takerOrderId;

    private String clientOid;

    private BigDecimal price;

    private Long orderTime;

    private String reason;
}
