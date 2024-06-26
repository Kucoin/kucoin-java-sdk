/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.rest.response.HFTradeResponse;
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
     * @param tradeType   TRADE, MARGIN_TRADE
     * @param startAt     [optional] Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       [optional] End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param pageSize
     * @param currentPage
     * @return Trades.
     */
    Pagination<TradeResponse> listFills(String symbol, String orderId, String side, String type, String tradeType,
                                        Long startAt, Long endAt, int pageSize, int currentPage) throws IOException;

    /**
     * Request via this endpoint to get a list of 1000 fills in the last 24 hours.
     *
     * @param pageSize
     * @param currentPage
     * @return Trades.
     */
    @Deprecated
    Pagination<TradeResponse> queryLimitFillsPageList(int pageSize, int currentPage) throws IOException;

    /**
     * Request via this endpoint to get a list of 1000 fills in the last 24 hours.
     *
     * @return TradeResponse
     * @throws IOException
     */
    List<TradeResponse> queryLimitFillsList() throws IOException;

    /**
     * This endpoint can be used to obtain a list of the latest HF transaction details.
     * The returned results are paginated.
     * The data is sorted in descending order according to time.
     *
     * @param symbol Only returns order information for the specified trading pair
     * @param orderId [optional] Look up the transaction details pertaining to the order id（IforderId is specified，please ignore the other query parameters）
     * @param side [optional] buy（Buy） or sell（Sell）
     * @param type [optional] Order type: limit（limit order）, market(market order)
     * @param startAt [optional] Start time（ms），puts a restriction on the transaction(creation) time for the transaction records
     * @param endAt [optional] End time（ms），puts a restriction on the transaction(creation) time of the transaction records
     * @param lastId [optional] The id of the last data item from the previous batch, defaults to obtaining the latest data
     * @param limit [optional] Default100，maximum 200
     * @return TradeResponse
     * @throws IOException
     */
    HFTradeResponse queryHFTrades(String symbol, String orderId, String side, String type,
                                  Long startAt, Long endAt, Long lastId, Integer limit) throws IOException;
}
