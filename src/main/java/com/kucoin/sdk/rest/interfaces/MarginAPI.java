/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.MarginAccountResponse;
import com.kucoin.sdk.rest.response.MarginConfigResponse;
import com.kucoin.sdk.rest.response.MarkPriceResponse;

import java.io.IOException;

/**
 * Created by ezreal on 2020/12/08.
 */
public interface MarginAPI {

    /**
     * Get Mark Price
     * <p>
     * Request via this endpoint to get the index price of the specified symbol.
     * </p>
     * @param symbol
     * @return
     */
    MarkPriceResponse getMarkPrice(String symbol) throws IOException;

    /**
     * Get Margin Configuration Info
     * <p>
     * Request via this endpoint to get the configure info of the margin.
     * </p>
     * @return
     */
    MarginConfigResponse getMarginConfig() throws IOException;

    /**
     * Get Margin Account
     * <p>
     * Request via this endpoint to get the info of the margin account.
     * </p>
     * @return
     */
    MarginAccountResponse getMarginAccount() throws IOException;

}
