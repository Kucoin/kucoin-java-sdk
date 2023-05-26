/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.OrderBookResponse;

import java.io.IOException;

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
    OrderBookResponse getTop100Level2OrderBook(String symbol) throws IOException;

    /**
     * Get a list of open orders for a symbol.
     * Level-2 order book includes all bids and asks (aggregated by price), this level return only one size for each active price (as if there was only a single order for that size at the level).
     * This API will return a part of Order Book within 20 depth for each side(ask or bid).
     * It is recommended to use in most cases, it is the fastest Order Book API, and reduces traffic usage.
     * To maintain up-to-date Order Book in real time, please use it with Websocket Feed.
     *
     * @param symbol
     * @return The aggregated part order book.
     */
    OrderBookResponse getTop20Level2OrderBook(String symbol) throws IOException;

    /**
     * Request via this endpoint to get the order book of the specified symbol.
     * Level 2 order book includes all bids and asks (aggregated by price).
     * This level returns only one aggregated size for each price (as if there was only one single order for that price).
     * This API will return data with full depth.
     *
     * @param symbol
     * @return The aggregated full order book.
     */
    OrderBookResponse getAllLevel2OrderBook(String symbol) throws IOException ;

}
