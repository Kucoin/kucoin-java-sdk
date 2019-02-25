/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.rest.response.TradeHistoryResponse;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface HistoryAPI {

    /**
     * List the latest trades for a symbol.
     *
     * @param symbol
     * @return The trades for the symbol.
     */
    List<TradeHistoryResponse> getTradeHistories(String symbol) throws IOException;

    /**
     * Historic rates for a symbol. Rates are returned in grouped buckets based on requested type.
     *
     * @param symbol
     * @param startAt Start time, unix timestamp calculated in seconds not millisecond
     * @param endAt   End time, unix timestamp calculated in seconds not millisecond
     * @param type    Type of candlestick patterns, The type field must be one of the following values:
     *                {"1min","3min","5min","15min","30min","1hour","2hour","4hour","6hour","8hour", "12hour", "1day","1week"}.
     * @return
     * [
     *  time, bucket start time
     *  open, opening price (first trade) in the bucket interval
     *  close, closing price (last trade) in the bucket interval
     *  high, highest price during the bucket interval
     *  low, lowest price during the bucket interval
     *  volume, volume of trading activity during the bucket interval
     *  turnover Turnover of a period of time.
     *           The turnover is the sum of the transaction volumes of all orders (Turnover of each order=price*quantity).
     * ]
     */
    List<List<String>> getHistoricRates(String symbol, long startAt, long endAt, String type) throws IOException;


}
