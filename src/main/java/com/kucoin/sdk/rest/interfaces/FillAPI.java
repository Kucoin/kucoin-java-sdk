/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.TradeResponse;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface FillAPI {

    /**
     * Get a list of recent fills.
     *
     * @param symbol      [optional] Limit list of fills to this orderId
     * @param orderId     [optional] Limit list of fills to this orderId
     * @param side        [optional] buy or sell
     * @param type        [optional] limit, market, limit_stop or market_stop
     * @param startAt     [optional] Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       [optional] End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param pageSize
     * @param currentPage
     * @return Trades.
     */
    Pagination<TradeResponse> listFills(String symbol, String orderId, String side, String type,
                                        Long startAt, Long endAt, int currentPage, int pageSize);
}
