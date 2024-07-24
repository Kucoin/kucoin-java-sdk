/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.MarginOrderCreateRequest;
import com.kucoin.sdk.rest.request.UserLeverageUpdateRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

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

    /**
     * You can place two types of orders: limit and market. Orders can only be placed if your account has sufficient funds.
     * Once an order is placed, your account funds will be put on hold for the duration of the order.
     * How much and which funds are put on hold depends on the order type and parameters specified. See the Holds details below.
     * @param request
     * @return
     */
    MarginOrderCreateResponse createMarginOrder(MarginOrderCreateRequest request) throws IOException;

    /**
     * Order test endpoint, the request parameters and return parameters of this endpoint are exactly the same as the order endpoint, and can be used to verify whether the signature is correct and other operations.
     * After placing an order, the order will not enter the matching system, and the order cannot be queried.
     *
     * @param request
     * @return
     * @throws IOException
     */
    MarginOrderCreateResponse createMarginOrderTest(MarginOrderCreateRequest request) throws IOException;

    /**
     *  Get Margin Price Strategy
     *  <p>
     *  Request via this endpoint to get the cross/isolated margin risk limit.
     *  </p>
     * @param marginModel
     * @return
     */
    @Deprecated
    List<MarginPriceStrategyResponse> getMarginPriceStrategy(String marginModel) throws IOException;

    /**
     * Get Leveraged Token Info
     * @param currency
     * @return EtfInfoResponse
     * @throws IOException
     */
    List<EtfInfoResponse> getEtfInfo(String currency) throws IOException;

    /**
     * This interface can obtain the risk limit and currency configuration of cross margin.
     *
     * @param symbol
     * @param currency
     * @return
     */
    List<CrossMarginCurrencyResponse> getMarginCurrencies(String symbol, String currency) throws IOException;

    /**
     * Get Account Detail - Cross Margin
     * @param quoteCurrency currently only supports USDT, KCS, BTC, USDT as default
     * @param queryType Query account type (default MARGIN), MARGIN - only query low frequency cross margin account, MARGIN_V2-only query high frequency cross margin account, ALL - consistent aggregate query with the web side
     * @return
     */
    MarginAccountResponse getMarginAccounts(String quoteCurrency, String queryType) throws IOException;

    /**
     * Get Cross Margin Trading Pairs Configuration
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-trading-v3-/get-cross-margin-trading-pairs-configuration">ApiDoc</a>
     *
     * @param symbol Optional. If not provided, all cross margin trading pairs will be queried. If provided, only the specified trading pair will be queried.
     * @return The configuration of cross margin trading pairs.
     * @throws IOException IOException
     */
    MarginSymbolsResponse getMarginSymbols(String symbol) throws IOException;

    /**
     * Modify Leverage Multiplier
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-trading-v3-/modify-leverage-multiplier">ApiDoc</a>
     *
     * @param request UserLeverageUpdateRequest
     * @return Void
     * @throws IOException IOException
     */
    Void updateUserLeverage(UserLeverageUpdateRequest request) throws IOException;

}
