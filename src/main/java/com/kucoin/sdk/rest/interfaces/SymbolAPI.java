/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.util.List;

import com.kucoin.sdk.rest.response.SymbolResponse;
import com.kucoin.sdk.rest.response.SymbolTickResponse;
import com.kucoin.sdk.rest.response.TickerResponse;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface SymbolAPI {

    /**
     * Get a list of available currency pairs for trading.
     *
     * @return The available symbols.
     */
    List<SymbolResponse> getSymbols();

    /**
     * Ticker include only the inside (i.e. best) bid and ask data , last price and last trade size.
     *
     * @param symbol
     * @return The ticker.
     */
    TickerResponse getTicker(String symbol);

    /**
     * Get 24 hr stats for the symbol.
     * volume is in base currency units. open, high, low are in quote currency units.
     *
     * @param symbol
     * @return The 24hr stats for the symbol.
     */
    SymbolTickResponse get24hrStats(String symbol);

}
