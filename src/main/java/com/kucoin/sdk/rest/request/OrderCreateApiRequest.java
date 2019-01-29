/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 订单创建对象
 *
 * @author 屈亮
 * @since 2018-09-17
 */
@Getter
@Builder
public class OrderCreateApiRequest {

    /**
     * a valid trading symbol code. e.g. ETH-BTC
     */
    private String symbol;

    /**
     * [optional] limit or market (default is limit)
     */
    @Builder.Default
    private String type = "limit";

    /**
     * buy or sell
     */
    private String side;

    /**
     * price per base currency
     */
    private BigDecimal price;

    /**
     * amount of base currency to buy or sell
     */
    private BigDecimal size;

    /**
     * [optional] Desired amount of quote currency to use
     */
    private BigDecimal funds;

    /**
     * [optional] self trade protect , CN, CO, CB or DC
     */
    @Builder.Default
    private String stp = "";

    /**
     * [optional] Either loss or entry. Requires stopPrice to be defined
     */
    @Builder.Default
    private String stop = "";

    /**
     * [optional] Only if stop is defined. Sets trigger price for stop order
     */
    private BigDecimal stopPrice;

    /**
     * [optional] GTC, GTT, IOC, or FOK (default is GTC)
     */
    @Builder.Default
    private String timeInForce = "GTC";

    /**
     * [optional] * cancel after n seconds
     */
    private long cancelAfter;

    /**
     * [optional] ** Post only flag
     */
    private boolean postOnly = false;

    /**
     * [optional] Orders not displayed in order book
     */
    private boolean hidden = false;

    /**
     * [optional] Only visible portion of the order is displayed in the order book
     */
    private boolean iceberge = false;

    /**
     * [optional] The maximum visible size of an iceberg order
     */
    private BigDecimal visibleSize;

    /**
     * Unique order id selected by you to identify your order e.g. UUID
     */
    private String clientOid;

    /**
     * [optional] remark for the order, length cannot exceed 100 utf8 characters
     */
    private String remark;
}