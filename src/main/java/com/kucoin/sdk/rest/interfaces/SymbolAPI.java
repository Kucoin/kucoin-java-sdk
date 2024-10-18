/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.*;

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
    @Deprecated
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

    /**
     * Request via this endpoint to get a list of available currency pairs for trading.
     *
     * @param market [Optional] The trading market
     * @return The available symbols.
     */
    List<SymbolResponse> getSymbolList(String market) throws IOException;

    /**
     * Request via this endpoint to get detail currency pairs for trading.
     * If you want to get the market information of the trading symbol, please use getSymbolList
     * @param symbol
     * @return symbol information
     */
    SymbolResponse getSymbolDetail(String symbol) throws IOException;
}
