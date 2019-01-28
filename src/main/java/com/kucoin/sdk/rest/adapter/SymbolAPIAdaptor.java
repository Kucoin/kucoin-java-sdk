package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.SymbolAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.SymbolAPIRetrofit;
import com.kucoin.sdk.rest.response.SymbolResponse;
import com.kucoin.sdk.rest.response.SymbolTickResponse;
import com.kucoin.sdk.rest.response.TickerResponse;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class SymbolAPIAdaptor extends PublicRetrofitAPIImpl<SymbolAPIRetrofit> implements SymbolAPI {

    public SymbolAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<SymbolResponse> getSymbols() {
        return super.executeSync(getAPIImpl().getSymbols());
    }

    @Override
    public TickerResponse getTicker(String symbol) {
        return super.executeSync(getAPIImpl().getTicker(symbol));
    }

    @Override
    public SymbolTickResponse get24hrStats(String symbol) {
        return super.executeSync(getAPIImpl().getMarketStats(symbol));
    }
}
