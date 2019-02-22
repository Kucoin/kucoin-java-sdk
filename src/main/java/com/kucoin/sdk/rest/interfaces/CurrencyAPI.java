/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * Get fiat price for currency
     *
     * @param base       [optional] Fiat,eg.USD,EUR, default is USD
     * @param currencies [optional] Cryptocurrencies.For multiple cyrptocurrencies, please separate them with comma one by one. default is all
     * @return
     */
    Map<String, BigDecimal> getFiatPrice(String base, String currencies);
}
