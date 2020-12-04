/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.rest.request.MultiOrderCreateRequest;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.response.ActiveOrderResponse;
import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.MultiOrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.UserFeeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
     * Place Bulk Orders
     * <p>
     * Request via this endpoint to place 5 orders at the same time. The order type must be a limit order of the same symbol. The interface currently only supports spot trading
     * </p>
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
     * @param symbols
     * @return
     */
    List<UserFeeResponse> getUserTradeFees(String symbols) throws IOException;

}
