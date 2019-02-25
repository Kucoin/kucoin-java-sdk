/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.CurrencyAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.CurrencyAPIRetrofit;
import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class CurrencyAPIAdaptor extends PublicRetrofitAPIImpl<CurrencyAPIRetrofit> implements CurrencyAPI {

    public CurrencyAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<CurrencyResponse> getCurrencies() throws IOException {
        return super.executeSync(getAPIImpl().getCurrencies());
    }

    @Override
    public CurrencyDetailResponse getCurrencyDetail(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getCurrencyDetail(currency));
    }
}
