/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyDetailV2Response;
import com.kucoin.sdk.rest.response.CurrencyDetailV3Response;
import com.kucoin.sdk.rest.response.CurrencyResponse;

import java.io.IOException;
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
     * Get single currency detail.return all chain if chain is null.
     *
     * @param currency the code of the currency
     * @param chain the name of the chain
     * @return Currency detail.
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    CurrencyDetailV2Response getCurrencyDetailV2(String currency, String chain) throws IOException;

    /**
     * List known currencies detail.
     *
     * @return Currencies.
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<CurrencyDetailV2Response> getCurrenciesV3() throws IOException;

    /**
     * Get fiat price for currency
     *
     * @param base       [optional] Fiat,eg.USD,EUR, default is USD
     * @param currencies [optional] Cryptocurrencies.For multiple cyrptocurrencies, please separate them with comma one by one. default is all
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    Map<String, BigDecimal> getFiatPrice(String base, String currencies) throws IOException;

    /**
     * Get Currency Detail (V3)
     * <a href="https://www.kucoin.com/docs/rest/spot-trading/market-data/get-currency-detail">ApiDoc</a>
     *
     * @param currency Path parameter, Currency
     * @param chain Support for querying the chain of currency, e.g. The available value for USDT are OMNI, ERC20, TRC20. This only apply for multi-chain currency, and there is no need for single chain currency.
     * @return The currency details of a specified currency
     */
    CurrencyDetailV3Response getCurrencyDetailV3(String currency, String chain) throws IOException;
}
