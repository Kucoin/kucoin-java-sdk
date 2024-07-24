/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.kucoin.sdk.rest.interfaces.*;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface KucoinRestClient {

    UserAPI userAPI();

    AccountAPI accountAPI();

    DepositAPI depositAPI();

    FillAPI fillAPI();

    OrderAPI orderAPI();

    WithdrawalAPI withdrawalAPI();

    SymbolAPI symbolAPI();

    OrderBookAPI orderBookAPI();

    HistoryAPI historyAPI();

    CurrencyAPI currencyAPI();

    TimeAPI timeAPI();

    CommonAPI commonAPI();

    StopOrderAPI stopOrderAPI();

    MarginAPI marginAPI();

    LoanAPI loanAPI();

    IsolatedAPI isolatedAPI();

    OcoOrderAPI ocoOrderAPI();

    EarnAPI earnAPI();

    VipLendingAPI vipLendingAPI();

    HFMarginAPI HFMarginAPI();
}
