/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface CurrencyAPI {

    /**
     * List known currencies.
     *
     * @return
     */
    List<CurrencyResponse> getCurrencies();

    /**
     * Get single currency detail
     *
     * @param currency the code of the currency
     * @return
     */
    CurrencyDetailResponse getCurrencyDetail(String currency);
}
