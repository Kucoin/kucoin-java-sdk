/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.impl;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.rest.interfaces.AccountAPI;
import com.kucoin.sdk.rest.interfaces.CurrencyAPI;
import com.kucoin.sdk.rest.interfaces.DepositAPI;
import com.kucoin.sdk.rest.interfaces.FillAPI;
import com.kucoin.sdk.KucoinRestClient;
import com.kucoin.sdk.rest.interfaces.HistoryAPI;
import com.kucoin.sdk.rest.interfaces.OrderAPI;
import com.kucoin.sdk.rest.interfaces.OrderBookAPI;
import com.kucoin.sdk.rest.interfaces.SymbolAPI;
import com.kucoin.sdk.rest.interfaces.TimeAPI;
import com.kucoin.sdk.rest.interfaces.WithdrawalAPI;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public class KucoinRestClientImpl implements KucoinRestClient {

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

    public KucoinRestClientImpl(KucoinClientBuilder kucoinBuilder) {
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

}
