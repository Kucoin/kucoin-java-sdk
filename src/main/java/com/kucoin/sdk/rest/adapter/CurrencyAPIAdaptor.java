/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.CurrencyAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.CurrencyAPIRetrofit;
import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyDetailV2Response;
import com.kucoin.sdk.rest.response.CurrencyDetailV3Response;
import com.kucoin.sdk.rest.response.CurrencyResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    public CurrencyDetailResponse getCurrencyDetail(String currency, String chain) throws IOException {
        return super.executeSync(getAPIImpl().getCurrencyDetail(currency, chain));
    }


    @Override
    public CurrencyDetailV2Response getCurrencyDetailV2(String currency, String chain) throws IOException {
        return super.executeSync(getAPIImpl().getCurrencyDetailV2(currency, chain));
    }

    @Override
    public List<CurrencyDetailV2Response> getCurrenciesV3() throws IOException {
        return super.executeSync(getAPIImpl().getCurrenciesV3());
    }

    @Override
    public Map<String, BigDecimal> getFiatPrice(String base, String currencies) throws IOException {
        return super.executeSync(getAPIImpl().getFiatPrice(base, currencies));
    }

    @Override
    public CurrencyDetailV3Response getCurrencyDetailV3(String currency, String chain) throws IOException {
        return super.executeSync(getAPIImpl().getCurrencyDetailV3(currency, chain));
    }
}
