/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Data
public class AccountChangeEvent {

    private BigDecimal total;

    private BigDecimal available;

    private BigDecimal availableChange;

    private String currency;

    private BigDecimal hold;

    private BigDecimal holdChange;

    private String relationEvent;

    private RelationContext relationContext;

    private String relationEventId;

    private String time;

    private String accountId;

    @Data
    public static class RelationContext {

        private String symbol;

        private String orderId;
    }

}
