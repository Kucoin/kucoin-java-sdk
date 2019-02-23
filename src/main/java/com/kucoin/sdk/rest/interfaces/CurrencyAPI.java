/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.exception.KucoinApiException;
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
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<CurrencyResponse> getCurrencies() throws IOException;

    /**
     * Get single currency detail
     *
     * @param currency the code of the currency
     * @return Currency detail.
     * @throws IOException on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    CurrencyDetailResponse getCurrencyDetail(String currency) throws IOException;
}
