/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.impl;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import com.kucoin.sdk.rest.interfaces.*;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public class KucoinRestClientImpl implements KucoinRestClient {

    private UserAPI userAPI;

    private AccountAPI accountAPI;

    private DepositAPI depositAPI;

    private FillAPI fillAPI;

    private OrderAPI orderAPI;

    private WithdrawalAPI withdrawAPI;

    private SymbolAPI symbolAPI;

    private OrderBookAPI orderBookAPI;

    private HistoryAPI historyAPI;

    private CurrencyAPI currencyAPI;

    private TimeAPI timeAPI;

    private StopOrderAPI stopOrderAPI;

    public KucoinRestClientImpl(KucoinClientBuilder kucoinBuilder) {
        this.userAPI = kucoinBuilder.getUserAPI();
        this.accountAPI = kucoinBuilder.getAccountAPI();
        this.depositAPI = kucoinBuilder.getDepositAPI();
        this.fillAPI = kucoinBuilder.getFillAPI();
        this.orderAPI = kucoinBuilder.getOrderAPI();
        this.withdrawAPI = kucoinBuilder.getWithdrawalAPI();
        this.symbolAPI = kucoinBuilder.getSymbolAPI();
        this.orderBookAPI = kucoinBuilder.getOrderBookAPI();
        this.historyAPI = kucoinBuilder.getHistoryAPI();
        this.currencyAPI = kucoinBuilder.getCurrencyAPI();
        this.timeAPI = kucoinBuilder.getTimeAPI();
        this.stopOrderAPI = kucoinBuilder.getStopOrderAPI();
    }

    @Override
    public UserAPI userAPI() {
        return userAPI;
    }

    @Override
    public AccountAPI accountAPI() {
        return accountAPI;
    }

    @Override
    public DepositAPI depositAPI() {
        return depositAPI;
    }

    @Override
    public FillAPI fillAPI() {
        return fillAPI;
    }

    @Override
    public OrderAPI orderAPI() {
        return orderAPI;
    }

    @Override
    public WithdrawalAPI withdrawalAPI() {
        return withdrawAPI;
    }

    @Override
    public SymbolAPI symbolAPI() {
        return symbolAPI;
    }

    @Override
    public OrderBookAPI orderBookAPI() {
        return orderBookAPI;
    }

    @Override
    public HistoryAPI historyAPI() {
        return historyAPI;
    }

    @Override
    public CurrencyAPI currencyAPI() {
        return currencyAPI;
    }

    @Override
    public TimeAPI timeAPI() {
        return timeAPI;
    }

    @Override
    public StopOrderAPI stopOrderAPI() {
        return stopOrderAPI;
    }

}
