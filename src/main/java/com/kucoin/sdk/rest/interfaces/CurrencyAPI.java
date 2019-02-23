/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.util.List;

import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface CurrencyAPI {

    /**
     * List known currencies.
     *
     * @return Currencies.
     */
    List<CurrencyResponse> getCurrencies();

    /**
     * Get single currency detail
     *
     * @param currency the code of the currency
     * @return Currency detail.
     */
    CurrencyDetailResponse getCurrencyDetail(String currency);
}
