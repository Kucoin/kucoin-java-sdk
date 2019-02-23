/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.OrderBookResponse;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface OrderBookAPI {

    /**
     * Get a list of open orders for a symbol.
     * Level-2 order book includes all bids and asks (aggregated by price), this level return only one size for each active price (as if there was only a single order for that size at the level).
     * This API will return a part of Order Book within 100 depth for each side(ask or bid).
     * It is recommended to use in most cases, it is the fastest Order Book API, and reduces traffic usage.
     * To maintain up-to-date Order Book in real time, please use it with Websocket Feed.
     *
     * @param symbol
     * @return The aggregated part order book.
     */
    OrderBookResponse getPartOrderBookAggregated(String symbol);

    /**
     * Get a list of open orders for a symbol.
     * Level-2 order book includes all bids and asks (aggregated by price), this level return only one size for each active price (as if there was only a single order for that size at the level).
     * This API will return data with full depth.
     * It is generally used by professional traders because it uses more server resources and traffic, and we have strict access frequency control.
     * To maintain up-to-date Order Book in real time, please use it with Websocket Feed.
     *
     * @param symbol
     * @return The aggregated full order book.
     */
    OrderBookResponse getFullOrderBookAggregated(String symbol);

    /**
     * Get a list of open orders for a symbol. Level-3 order book includes all bids and asks (non-aggregated, each item in Level-3 means a single order).
     * Level 3 is non-aggregated and returns the entire order book.
     * This API is generally used by professional traders because it uses more server resources and traffic, and we have strict access frequency control.
     * To Maintain up-to-date Order Book in real time, please use it with Websocket Feed.
     *
     * @param symbol
     * @return The full atomic order book.
     */
    OrderBookResponse getFullOrderBookAtomic(String symbol);

}
