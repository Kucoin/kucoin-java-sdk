/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface OrderAPI {

    /**
     * Place a new order
     * <p>
     * You can place two types of orders: limit and market. Orders can only be placed if your account has sufficient funds.
     * Once an order is placed, your account funds will be put on hold for the duration of the order.
     * How much and which funds are put on hold depends on the order type and parameters specified.
     * <p>
     * The maximum matching orders for a single trading pair in one account is 50 (stop limit order included).
     *
     * @param opsRequest
     * @return A response containing the order id.
     */
    OrderCreateResponse createOrder(OrderCreateApiRequest opsRequest) throws IOException;

    /**
     * Place a new test order
     *
     * @param opsRequest
     * @return A response containing the order id.
     */
    OrderCreateResponse createOrderTest(OrderCreateApiRequest opsRequest) throws IOException;

    /**
     * Place Bulk Orders
     * <p>
     * Request via this endpoint to place 5 orders at the same time. The order type must be a limit order of the same symbol. The interface currently only supports spot trading
     * </p>
     *
     * @param multiOrderCreateRequest
     * @return
     * @throws IOException
     */
    MultiOrderCreateResponse createMultipleOrders(MultiOrderCreateRequest multiOrderCreateRequest) throws IOException;

    /**
     * Cancel an order
     * <p>
     * Cancel a previously placed order.
     *
     * @param orderId
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelOrder(String orderId) throws IOException;

    /**
     * Cancel Single Order by clientOid
     * <p>
     * Request via this interface to cancel an order via the clientOid.
     * </p>
     *
     * @param clientOid
     * @return
     * @throws IOException
     */
    OrderCancelResponse cancelOrderByClientOid(String clientOid) throws IOException;

    /**
     * With best effort, cancel all open orders. The response is a list of ids of the canceled orders.
     *
     * @param symbol
     * @param tradeType TRADE, MARGIN_TRADE
     * @return A response containing the ids of all open orders.
     */
    OrderCancelResponse cancelAllOrders(String symbol, String tradeType) throws IOException;

    /**
     * Get a single order by order id.
     *
     * @param orderId
     * @return The requested order.
     */
    OrderResponse getOrder(String orderId) throws IOException;

    /**
     * <p>
     * Request via this interface to check the information of a single active order via clientOid.
     * The system will prompt that the order does not exists if the order does not exist or has been settled.
     * </p>
     *
     * @param clientOid Unique order id created by users to identify their orders
     * @return
     * @throws IOException
     */
    ActiveOrderResponse getOrderByClientOid(String clientOid) throws IOException;

    /**
     * List your current orders.
     *
     * @param symbol      [optional] Only list orders for a specific symbol.
     * @param side        [optional] buy or sell
     * @param type        [optional] limit, market, limit_stop or market_stop
     * @param tradeType   TRADE, MARGIN_TRADE
     * @param status      [optional] active or done, done as default, Only list orders for a specific status .
     * @param startAt     [optional] Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       [optional] End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param pageSize
     * @param currentPage
     * @return A page of orders.
     */
    Pagination<OrderResponse> listOrders(String symbol, String side, String type, String tradeType, String status,
                                         Long startAt, Long endAt, int pageSize, int currentPage) throws IOException;

    /**
     * Actual fee rate of the trading pair
     * <p>
     * This interface is for the actual fee rate of the trading pair.
     * You can inquire about fee rates of 10 trading pairs each time at most.
     * The fee rate of your sub-account is the same as that of the master account.
     * </p>
     *
     * @param symbols
     * @return
     */
    List<UserFeeResponse> getUserTradeFees(String symbols) throws IOException;

    /**
     * This interface is for the basic fee rate of users
     *
     * @param currencyType [Optional] Currency type: 0-crypto currency, 1-fiat currency. default is 0-crypto currency
     * @return
     */
    UserFeeResponse getUserBaseFee(String currencyType) throws IOException;

    /**
     * Request via this endpoint to get 1000 orders in the last 24 hours.
     * Items are paginated and sorted to show the latest first.
     * See the Pagination section for retrieving additional entries after the first page.
     *
     * @param pageSize
     * @param currentPage
     * @return A page of orders.
     */
    @Deprecated
    Pagination<OrderResponse> queryLimitOrderPageList(int pageSize, int currentPage) throws IOException;

    /**
     * Request via this endpoint to get 1000 orders in the last 24 hours.
     *
     * @return
     * @throws IOException
     */
    List<OrderResponse> queryLimitOrderList() throws IOException;

    /**
     * Place hf order
     * https://docs.kucoin.com/spot-hf/#place-hf-order
     *
     * @param createRequest
     * @return
     * @throws IOException
     */
    HFOrderCreateResponse createHFOrder(HFOrderCreateRequest createRequest) throws IOException;

    /**
     * Order test endpoint, the request parameters and return parameters of this endpoint are exactly the same as the order endpoint,
     * and can be used to verify whether the signature is correct and other operations. After placing an order,
     * the order will not enter the matching system, and the order cannot be queried.
     *
     * @param createRequest
     * @return
     * @throws IOException
     */
    HFOrderCreateResponse createHFOrderTest(HFOrderCreateRequest createRequest) throws IOException;

    /**
     * Sync place hf order
     * https://docs.kucoin.com/spot-hf/#sync-place-hf-order
     *
     * @param createRequest
     * @return
     * @throws IOException
     */
    HFOrderSyncCreateResponse syncCreateHFOrder(HFOrderCreateRequest createRequest) throws IOException;

    /**
     * This endpoint supports sequential batch order placement from a single endpoint.
     * A maximum of 5orders can be placed simultaneously.
     * The order types must be limit orders of the same trading pair （this endpoint currently only supports spot trading and does not support margin trading）.
     * https://docs.kucoin.com/spot-hf/#place-multiple-hf-orders
     *
     * @param multiCreateRequest
     * @return
     * @throws IOException
     */
    List<HFOrderMultiCreateResponse> createMultipleHFOrders(HFOrderMultiCreateRequest multiCreateRequest) throws IOException;

    /**
     * Sync place multiple hf orders
     * https://docs.kucoin.com/spot-hf/#sync-place-multiple-hf-orders
     *
     * @param multiCreateRequest
     * @return
     * @throws IOException
     */
    List<HFOrderSyncMultiCreateResponse> syncCreateMultipleHFOrders(HFOrderMultiCreateRequest multiCreateRequest) throws IOException;

    /**
     * This interface can modify the price and quantity of the order according to orderId or clientOid.
     * https://docs.kucoin.com/spot-hf/#modify-order
     *
     * @param alterRequest
     * @return
     * @throws IOException
     */
    HFOrderAlterResponse alterHFOrder(HFOrderAlterRequest alterRequest) throws IOException;

    /**
     * This endpoint can be used to cancel a high-frequency order by orderId.
     * https://docs.kucoin.com/spot-hf/#cancel-orders-by-orderid
     *
     * @param orderId
     * @param symbol
     * @return
     * @throws IOException
     */
    HFOrderCancelResponse cancelHFOrder(String orderId, String symbol) throws IOException;

    /**
     * Sync cancel high-frequency orders by orderId
     * https://docs.kucoin.com/spot-hf/#sync-cancel-orders-by-orderid
     *
     * @param orderId
     * @param symbol
     * @return
     * @throws IOException
     */
    HFOrderSyncCancelResponse syncCancelHFOrder(String orderId, String symbol) throws IOException;

    /**
     * This endpoint sends out a request to cancel a high-frequency order using clientOid.
     * https://docs.kucoin.com/spot-hf/#cancel-order-by-clientoid
     *
     * @param clientOid
     * @param symbol
     * @return
     * @throws IOException
     */
    HFOrderCancelByClientOidResponse cancelHFOrderByClientOid(String clientOid, String symbol) throws IOException;

    /**
     * Sync cancel high-frequency orders by clientOid
     * https://docs.kucoin.com/spot-hf/#sync-cancel-orders-by-clientoid
     *
     * @param clientOid
     * @param symbol
     * @return
     * @throws IOException
     */
    HFOrderSyncCancelResponse syncCancelHFOrderByClientOid(String clientOid, String symbol) throws IOException;

    /**
     * This interface can cancel the specified quantity of the order according to the orderId.
     * https://docs.kucoin.com/spot-hf/#cancel-specified-number-of-orders-by-orderid
     *
     * @param orderId
     * @param symbol
     * @param cancelSize
     * @return
     * @throws IOException
     */
    HFOrderCancelSizeResponse cancelHFOrderSize(String orderId, String symbol, String cancelSize) throws IOException;

    /**
     * This endpoint allows cancellation of all orders related to a specific trading pair with a status of open (including all orders pertaining to high-frequency trading accounts and non-high-frequency trading accounts).
     * If the HTTP status of the interface is 200, it can be considered that the cancellation request has been submitted successfully.
     * https://docs.kucoin.com/spot-hf/#cancel-all-hf-orders-by-symbol
     *
     * @param symbol
     * @throws IOException
     */
    String cancelHFOrdersBySymbol(String symbol) throws IOException;

    /**
     * This endpoint can cancel all HF orders for all symbol.
     *
     * @return HFOrderCancelAllResponse
     * @throws IOException
     */
    HFOrderCancelAllResponse cancelAllHFOrders() throws IOException;

    /**
     * This endpoint obtains a list of all active HF orders. The return data is sorted in descending order based on the latest update times.
     * https://docs.kucoin.com/spot-hf/#obtain-list-of-active-hf-orders
     *
     * @param symbol
     * @return
     * @throws IOException
     */
    List<HFOrderResponse> getActiveHFOrders(String symbol) throws IOException;

    /**
     * This interface can query all trading pairs that the user has active orders
     * https://docs.kucoin.com/spot-hf/#obtain-list-of-symbol-with-active-hf-orders
     *
     * @return
     * @throws IOException
     */
    HFOrderActiveSymbolQueryResponse getActiveHFOrderSymbols() throws IOException;

    /**
     * This endpoint obtains a list of filled HF orders and returns paginated data.
     * The returned data is sorted in descending order based on the latest order update times.
     *
     * @param symbol  Only returns order information for the specified trading pair
     * @param side    [optional] buy (Buy) orsell (Sell)
     * @param type    [optional] Order type: limit (limit order), market(market order)
     * @param startAt [optional] Start time (ms)，last update(filled) time of the limit order
     * @param endAt   [optional] End time (ms)，last update(filled) time of limit order
     * @param lastId  [optional] The id of the last data item from the previous batch，defaults to obtaining the latest data
     * @param limit   [optional] Default20，maximum100
     * @return
     * @throws IOException
     */
    HFDoneOrderQueryResponse getDoneHFOrders(String symbol, String side, String type, Long startAt, Long endAt, Long lastId, Integer limit) throws IOException;

    /**
     * This endpoint can be used to obtain information for a single HF order using the order id.
     * https://docs.kucoin.com/spot-hf/#details-of-a-single-hf-order
     *
     * @param orderId
     * @param symbol
     * @return
     * @throws IOException
     */
    HFOrderResponse getHFOrder(String orderId, String symbol) throws IOException;

    /**
     * The endpoint can be used to obtain information about a single order using clientOid.
     * If the order does not exist, then there will be a prompt saying that the order does not exist.
     * https://docs.kucoin.com/spot-hf/#obtain-details-of-a-single-hf-order-using-clientoid
     *
     * @param clientOid
     * @param symbol
     * @return
     * @throws IOException
     */
    HFOrderResponse getHFOrderByClientOid(String clientOid, String symbol) throws IOException;

    /**
     * HF auto cancel setting
     * https://docs.kucoin.com/spot-hf/#hf-auto-cancel-setting
     *
     * @param timeout Auto cancel order trigger setting time,
     *                the unit is second. range: timeout=-1 (meaning unset) or 5 &lt;= timeout &lt;= 86400.
     *                For example, timeout=5 means that the order will be automatically canceled if no user request is received for more than 5 seconds.
     *                When this parameter is changed, the previous setting will be overwritten.
     * @param symbols List of trading pairs. When this parameter is not empty, separate it with commas and support up to 50 trading pairs.
     *                Empty means all trading pairs. When this parameter is changed, the previous setting will be overwritten.
     * @return
     * @throws IOException
     */
    HFOrderDeadCancelResponse deadCancelHFOrder(int timeout, String symbols) throws IOException;

    /**
     * Through this interface, you can query the settings of automatic order cancellation
     * https://docs.kucoin.com/spot-hf/#hf-auto-cancel-order-setting-query
     *
     * @return
     * @throws IOException
     */
    HFOrderDeadCancelQueryResponse queryHFOrderDeadCancel() throws IOException;


    ServerStatusResponse queryServerStatus() throws IOException;
}
