/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.FillAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.FillAPIRetrofit;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.TradeResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class FillAPIAdapter extends AuthRetrofitAPIImpl<FillAPIRetrofit> implements FillAPI {

    public FillAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public Pagination<TradeResponse> listFills(String symbol, String orderId, String side,
                                               String type, String tradeType, Long start, Long end,
                                               int pageSize, int currentPage) throws IOException {
        return executeSync(getAPIImpl().queryTrades(symbol, orderId, side, type, tradeType, start, end, pageSize, currentPage));
    }
}
