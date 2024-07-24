/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.MarginAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.MarginAPIRetrofit;
import com.kucoin.sdk.rest.request.MarginOrderCreateRequest;
import com.kucoin.sdk.rest.request.UserLeverageUpdateRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by ezreal on 2020/12/08.
 */
public class MarginAPIAdapter extends AuthRetrofitAPIImpl<MarginAPIRetrofit> implements MarginAPI {

    public MarginAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }


    @Override
    public MarkPriceResponse getMarkPrice(String symbol) throws IOException{
        return executeSync(getAPIImpl().getMarkPrice(symbol));
    }

    @Override
    public MarginConfigResponse getMarginConfig() throws IOException {
        return executeSync(getAPIImpl().getMarginConfig());
    }

    @Override
    public MarginAccountResponse getMarginAccount() throws IOException {
        return executeSync(getAPIImpl().getMarginAccount());
    }

    @Override
    public MarginOrderCreateResponse createMarginOrder(MarginOrderCreateRequest request) throws IOException {
        return executeSync(getAPIImpl().createMarginOrder(request));
    }

    @Override
    public MarginOrderCreateResponse createMarginOrderTest(MarginOrderCreateRequest request) throws IOException {
        return executeSync(getAPIImpl().createMarginOrderTest(request));
    }

    @Override
    public List<MarginPriceStrategyResponse> getMarginPriceStrategy(String marginModel) throws IOException {
        return executeSync(getAPIImpl().getMarginPriceStrategy(marginModel));
    }

    @Override
    public List<EtfInfoResponse> getEtfInfo(String currency) throws IOException {
        return executeSync(getAPIImpl().getEtfInfo(currency));
    }

    @Override
    public List<CrossMarginCurrencyResponse> getMarginCurrencies(String symbol, String currency) throws IOException {
        return executeSync(getAPIImpl().getMarginCurrencies(symbol, currency));
    }

    @Override
    public MarginAccountResponse getMarginAccounts(String quoteCurrency, String queryType) throws IOException {
        return executeSync(getAPIImpl().getMarginAccounts(quoteCurrency, queryType));
    }

    @Override
    public MarginSymbolsResponse getMarginSymbols(String symbol) throws IOException {
        return executeSync(getAPIImpl().getMarginSymbols(symbol));
    }

    @Override
    public Void updateUserLeverage(UserLeverageUpdateRequest request) throws IOException {
        return executeSync(getAPIImpl().updateUserLeverage(request));
    }
}
