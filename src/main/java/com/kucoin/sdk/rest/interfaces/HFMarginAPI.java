package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.HFMarginOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
public interface HFMarginAPI {

    /**
     * Get Active HF Order Symbols
     * This endpoint allows users to query all trading pairs with active orders.
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/get-active-hf-order-symbols">ApiDoc</a>
     *
     * @param tradeType [Mandatory] Cross Margin: MARGIN_TRADE, Isolated Margin: MARGIN_ISOLATED_TRADE
     * @return All trading pairs with active orders.
     * @throws IOException IOException
     */
    HfMarginOrderActiveSymbolsResponse getHFMarginOrderActiveSymbols(String tradeType) throws IOException;

    /**
     * Place HF Margin order
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/place-hf-order">ApiDoc</a>
     *
     * @param request HFMarginOrderCreateRequest
     * @return HFMarginOrderCreateResponse
     * @throws IOException IOException
     */
    HFMarginOrderCreateResponse createHFMarginOrder(HFMarginOrderCreateRequest request) throws IOException;

    /**
     * Place HF Order Test
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/place-hf-order-test">ApiDoc</a>
     *
     * @param request HFMarginOrderCreateRequest
     * @return HFMarginOrderCreateResponse
     * @throws IOException IOException
     */
    HFMarginOrderCreateResponse createHFMarginOrderTest(HFMarginOrderCreateRequest request) throws IOException;

    /**
     * Cancel HF order by clientOid
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/cancel-hf-order-by-clientoid">ApiDoc</a>
     *
     * @param clientOid Path parameter, an identifier created by the client
     * @param symbol symbol
     * @return HFMarginOrderCancelByClientOidResponse
     * @throws IOException IOException
     */
    HFMarginOrderCancelByClientOidResponse cancelHFMarginOrderByClientOid(String clientOid, String symbol) throws IOException;

    /**
     * Cancel HF order by orderId
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/cancel-hf-order-by-orderid">ApiDoc</a>
     *
     * @param orderId Path parameter, Order Id unique identifier
     * @param symbol symbol
     * @return HFMarginOrderCancelByOrderIdResponse
     * @throws IOException IOException
     */
    HFMarginOrderCancelByOrderIdResponse cancelHFMarginOrderByOrderId(String orderId, String symbol) throws IOException;

    /**
     * Cancel all HF orders by symbol
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/cancel-all-hf-orders-by-symbol">ApiDoc</a>
     *
     * @param symbol Cancel open orders pertaining to the specified trading pair
     * @param tradeType Transaction type: MARGIN_TRADE - cross margin trade, MARGIN_ISOLATED_TRADE - isolated margin trade
     * @return Since this interface is for batch cancellation of orders, the http status of the interface is 200.
     * @throws IOException IOException
     */
    String cancelAllHFMarginOrdersBySymbol(String symbol, String tradeType) throws IOException;

    /**
     * Get Active HF Orders List
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/get-active-hf-orders-list">ApiDoc</a>
     *
     * @param tradeType Order type: MARGIN_TRADE - cross margin trading order, MARGIN_ISOLATED_TRADE - isolated margin trading order
     * @param symbol Only returns order information for the specified trading pair
     * @return Active HF Orders List
     */
    List<HFMarginOrderResponse> getHFMarginActiveOrders(String tradeType, String symbol) throws IOException;

    /**
     * Get HF Filled List
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/get-hf-filled-list">ApiDoc</a>
     *
     * @param symbol    Mandatory, Only returns order information for the specified trading pair
     * @param tradeType Mandatory, Transaction type: MARGIN_TRADE - cross margin trade, MARGIN_ISOLATED_TRADE - isolated margin trade
     * @param side      buy (Buy) orsell (Sell)
     * @param type      Order type: limit (limit order), market(market order)
     * @param startAt   Start time (ms)，last update(filled) time of the limit order
     * @param endAt     End time (ms)，last update(filled) time of limit order
     * @param lastId    The id of the last data item from the previous batch，defaults to obtaining the latest data
     * @param limit     Default100，maximum200
     * @return HF Filled List
     * @throws IOException IOException
     */
    HFMarginOrderListResponse getHFMarginDoneOrders(String symbol, String tradeType,
                                                      String side, String type,
                                                      Long startAt, Long endAt,
                                                      Long lastId, Integer limit) throws IOException;
    /**
     * Get HF Order details by orderId
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/get-hf-order-details-by-orderid">ApiDoc</a>
     *
     * @param orderId Mandatory, Only returns order information for the specified trading pair
     * @param symbol Mandatory, Only returns order information for the specified trading pair
     * @return Information for a single margin HF order using the order id.
     * @throws IOException IOException
     */
    HFMarginOrderResponse getHFMarginOrderByOrderId(String orderId, String symbol) throws IOException;

    /**
     * Get HF order details by clientOid
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/get-hf-order-details-by-clientoid">ApiDoc</a>
     *
     * @param clientOid Mandatory, Path parameter，an identifier created by the client
     * @param symbol Mandatory, symbol, such as, ETH-BTC
     * @return Information for a single margin HF order using the clientOid.
     * @throws IOException IOException
     */
    HFMarginOrderResponse getHFMarginOrderByClientOid(String clientOid, String symbol) throws IOException;

    /**
     * Get HF transaction records
     * <a href="https://www.kucoin.com/docs/rest/margin-trading/margin-hf-trade/get-hf-transaction-records">ApiDoc</a>
     *
     * @param orderId Look up the transaction details pertaining to the order id（IforderId is specified，please ignore the other query parameters）
     * @param symbol Mandatory, Only returns order information for the specified trading pair
     * @param tradeType Mandatory, Trade type: MARGIN_TRADE - cross margin trade, MARGIN_ISOLATED_TRADE - isolated margin trade
     * @param side buy（Buy） or sell（Sell）
     * @param type Order type: limit（limit order）, market(market order)
     * @param startAt Start time（ms），puts a restriction on the transaction(creation) time for the transaction records
     * @param endAt End time（ms），puts a restriction on the transaction(creation) time of the transaction records
     * @param lastId The id of the last data item from the previous batch, defaults to obtaining the latest data
     * @param limit Default100，maximum 200
     * @return A list of the latest margin HF transaction details
     * @throws IOException IOException
     */
    HFMarginFillsResponse getHFMarginFills(String orderId, String symbol, String tradeType,
                                           String side, String type,
                                           Long startAt, Long endAt,
                                           Long lastId, Integer limit) throws IOException;
}
