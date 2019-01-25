package com.kucoin.sdk;

import com.kucoin.sdk.rest.interfaces.AccountAPI;
import com.kucoin.sdk.rest.interfaces.CurrencyAPI;
import com.kucoin.sdk.rest.interfaces.DepositAPI;
import com.kucoin.sdk.rest.interfaces.FillAPI;
import com.kucoin.sdk.rest.interfaces.HistoryAPI;
import com.kucoin.sdk.rest.interfaces.OrderAPI;
import com.kucoin.sdk.rest.interfaces.OrderBookAPI;
import com.kucoin.sdk.rest.interfaces.SymbolAPI;
import com.kucoin.sdk.rest.interfaces.TimeAPI;
import com.kucoin.sdk.rest.interfaces.WithdrawalAPI;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface KucoinRestClient {

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

}
