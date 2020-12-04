/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: yi.yang
 * @date: Created by yi.yang on 2020/3/25.
 * @Description: 止损单创建订单订单请求
 */
@Data
@Builder
public class StopOrderCreateRequest {

    private String id;

    private String symbol;

    private String userId;

    private String type;

    private String side;

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal funds;

    private BigDecimal takerFeeRate;

    private BigDecimal makerFeeRate;

    private String feeCurrency;

    private String stp;

    private String stop;

    private BigDecimal stopPrice;

    private String timeInForce;

    private Long cancelAfter;

    private boolean postOnly;

    private boolean hidden;

    private boolean iceberge;

    private BigDecimal visibleSize;

    private String channel;

    private String clientOid;

    private String remark;

    private String tags;

    private Long orderTime;

    private String domainId;

    private String tradeType;

    private Date createdAt;
}
