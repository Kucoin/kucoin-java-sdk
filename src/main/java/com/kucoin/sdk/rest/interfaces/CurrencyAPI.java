/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.exception.KucoinApiException;
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
     * @return Currencies.
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<CurrencyResponse> getCurrencies() throws IOException;

    /**
     * Get single currency detail
     *
     * @param currency the code of the currency
     * @param chain the name of the chain
     * @return Currency detail.
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    CurrencyDetailResponse getCurrencyDetail(String currency, String chain) throws IOException;

    /**
     * Get fiat price for currency
     *
     * @param base       [optional] Fiat,eg.USD,EUR, default is USD
     * @param currencies [optional] Cryptocurrencies.For multiple cyrptocurrencies, please separate them with comma one by one. default is all
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    Map<String, BigDecimal> getFiatPrice(String base, String currencies) throws IOException;
}
