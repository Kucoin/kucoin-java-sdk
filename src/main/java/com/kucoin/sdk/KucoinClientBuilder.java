/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import java.io.IOException;

import com.kucoin.sdk.rest.adapter.*;
import com.kucoin.sdk.rest.interfaces.*;
import org.apache.commons.lang3.StringUtils;

import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.impl.KucoinPrivateWSClientImpl;
import com.kucoin.sdk.impl.KucoinPublicWSClientImpl;
import com.kucoin.sdk.impl.KucoinRestClientImpl;
import com.kucoin.sdk.websocket.ChooseServerStrategy;
import com.kucoin.sdk.websocket.RandomChooseStrategy;

import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/9.
 */
@Getter
public class KucoinClientBuilder {

    private String apiKey;

    private String secret;

    private String passPhrase;

    private String baseUrl;

    private UserAPI userAPI;

    private AccountAPI accountAPI;

    private DepositAPI depositAPI;

    private FillAPI fillAPI;

    private OrderAPI orderAPI;

    private WithdrawalAPI withdrawalAPI;

    private CurrencyAPI currencyAPI;

    private TimeAPI timeAPI;

    private SymbolAPI symbolAPI;

    private OrderBookAPI orderBookAPI;

    private HistoryAPI historyAPI;

    private ChooseServerStrategy chooseServerStrategy;

    public KucoinRestClient buildRestClient() {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (userAPI == null) userAPI = new UserAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (accountAPI == null) accountAPI = new AccountAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (depositAPI == null) depositAPI = new DepositAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (withdrawalAPI == null) withdrawalAPI = new WithdrawalAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (fillAPI == null) fillAPI = new FillAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (orderAPI == null) orderAPI = new OrderAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (currencyAPI == null) currencyAPI = new CurrencyAPIAdaptor(baseUrl);
        if (timeAPI == null) timeAPI = new TimeAPIAdapter(baseUrl);
        if (symbolAPI == null) symbolAPI = new SymbolAPIAdaptor(baseUrl);
        if (orderBookAPI == null) orderBookAPI = new OrderBookAPIAdapter(baseUrl);
        if (historyAPI == null) historyAPI = new HistoryAPIAdapter(baseUrl);
        return new KucoinRestClientImpl(this);
    }

    public KucoinPublicWSClient buildPublicWSClient() throws IOException {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (chooseServerStrategy == null) chooseServerStrategy = new RandomChooseStrategy();
        KucoinPublicWSClientImpl client = new KucoinPublicWSClientImpl(this);
        client.connect();
        return client;
    }

    public KucoinPrivateWSClient buildPrivateWSClient() throws IOException {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (chooseServerStrategy == null) chooseServerStrategy = new RandomChooseStrategy();
        KucoinPrivateWSClientImpl client = new KucoinPrivateWSClientImpl(this);
        client.connect();
        return client;
    }

    public KucoinClientBuilder withApiKey(String apiKey, String secret, String passPhrase) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        return this;
    }

    public KucoinClientBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public KucoinClientBuilder withDepositAPI(DepositAPI depositAPI) {
        this.depositAPI = depositAPI;
        return this;
    }

    public KucoinClientBuilder withFillAPI(FillAPI fillAPI) {
        this.fillAPI = fillAPI;
        return this;
    }

    public KucoinClientBuilder withOrderAPI(OrderAPI orderAPI) {
        this.orderAPI = orderAPI;
        return this;
    }

    public KucoinClientBuilder withWithdrawalAPI(WithdrawalAPI withdrawalAPI) {
        this.withdrawalAPI = withdrawalAPI;
        return this;
    }

    public KucoinClientBuilder withAccountAPI(AccountAPI accountAPI) {
        this.accountAPI = accountAPI;
        return this;
    }

    public KucoinClientBuilder withSymbolAPI(SymbolAPI symbolAPI) {
        this.symbolAPI = symbolAPI;
        return this;
    }

    public KucoinClientBuilder withOrderBookAPI(OrderBookAPI orderBookAPI) {
        this.orderBookAPI = orderBookAPI;
        return this;
    }

    public KucoinClientBuilder withHistoryAPI(HistoryAPI historyAPI) {
        this.historyAPI = historyAPI;
        return this;
    }

    public KucoinClientBuilder withCurrencyAPI(CurrencyAPI currencyAPI) {
        this.currencyAPI = currencyAPI;
        return this;
    }

    public KucoinClientBuilder withTimeAPI(TimeAPI timeAPI) {
        this.timeAPI = timeAPI;
        return this;
    }

    public KucoinClientBuilder withChooseServerStrategy(ChooseServerStrategy chooseServerStrategy) {
        this.chooseServerStrategy = chooseServerStrategy;
        return this;
    }
}
