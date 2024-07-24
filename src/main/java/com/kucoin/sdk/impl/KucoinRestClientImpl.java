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

    private final UserAPI userAPI;

    private final AccountAPI accountAPI;

    private final DepositAPI depositAPI;

    private final FillAPI fillAPI;

    private final OrderAPI orderAPI;

    private final WithdrawalAPI withdrawAPI;

    private final SymbolAPI symbolAPI;

    private final OrderBookAPI orderBookAPI;

    private final HistoryAPI historyAPI;

    private final CurrencyAPI currencyAPI;

    private final TimeAPI timeAPI;

    private final StopOrderAPI stopOrderAPI;

    private final CommonAPI commonAPI;

    private final MarginAPI marginAPI;

    private final LoanAPI loanAPI;

    private final IsolatedAPI isolatedAPI;

    private final OcoOrderAPI ocoOrderAPI;

    private final EarnAPI earnAPI;

    private final VipLendingAPI vipLendingAPI;

    private final HFMarginAPI hfMarginAPI;

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
        this.commonAPI = kucoinBuilder.getCommonAPI();
        this.marginAPI = kucoinBuilder.getMarginAPI();
        this.loanAPI = kucoinBuilder.getLoanAPI();
        this.isolatedAPI = kucoinBuilder.getIsolatedAPI();
        this.ocoOrderAPI = kucoinBuilder.getOcoOrderAPI();
        this.earnAPI = kucoinBuilder.getEarnAPI();
        this.vipLendingAPI = kucoinBuilder.getVipLendingAPI();
        this.hfMarginAPI = kucoinBuilder.getHfMarginAPI();
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
    public CommonAPI commonAPI() {
        return commonAPI;
    }

    @Override
    public StopOrderAPI stopOrderAPI() {
        return stopOrderAPI;
    }

    @Override
    public MarginAPI marginAPI() {
        return marginAPI;
    }

    @Override
    public LoanAPI loanAPI() {
        return loanAPI;
    }

    @Override
    public IsolatedAPI isolatedAPI() {
        return isolatedAPI;
    }

    @Override
    public OcoOrderAPI ocoOrderAPI() {
        return ocoOrderAPI;
    }

    @Override
    public EarnAPI earnAPI() {
        return earnAPI;
    }

    @Override
    public VipLendingAPI vipLendingAPI() {
        return vipLendingAPI;
    }

    @Override
    public HFMarginAPI HFMarginAPI() {
        return hfMarginAPI;
    }

}
