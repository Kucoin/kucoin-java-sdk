/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.model;

import lombok.Data;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Data
public class OrderBook {

    /**
     * [price, size] for aggregated, [orderId, price, size] for atomic
     */
    private List<List<String>> asks;

    /**
     * [price, size] for aggregated, [orderId, price, size] for atomic
     */
    private List<List<String>> bids;

}
