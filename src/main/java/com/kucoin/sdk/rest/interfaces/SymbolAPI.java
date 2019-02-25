/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.AllTickersResponse;
import com.kucoin.sdk.rest.response.SymbolResponse;
import com.kucoin.sdk.rest.response.SymbolTickResponse;
import com.kucoin.sdk.rest.response.TickerResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface SymbolAPI {

    /**
     * Get a list of available currency pairs for trading.
     *
     * @return The available symbols.
     */
    List<SymbolResponse> getSymbols() throws IOException;

    /**
     * Ticker include only the inside (i.e. best) bid and ask data , last price and last trade size.
     *
     * @param symbol
     * @return The ticker.
     */
    TickerResponse getTicker(String symbol) throws IOException;

    /**
     * Require market ticker for all trading pairs in the market (including 24h volume).
     *
     * @return all tickers
     */
    AllTickersResponse getAllTickers() throws IOException;

    /**
     * Get 24 hr stats for the symbol.
     * volume is in base currency units. open, high, low are in quote currency units.
     *
     * @param symbol
     * @return The 24hr stats for the symbol.
     */
    SymbolTickResponse get24hrStats(String symbol) throws IOException;

    /**
     * Get the transaction currency for the entire trading market.
     *
     * @return market list
     */
    List<String> getMarketList() throws IOException;
}
