/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.StopOrderCancelRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.request.StopOrderQueryRequest;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.StopOrderResponse;

import java.io.IOException;
import java.util.List;

public interface StopOrderAPI {

    /**
     * Place a new stop order
     * <p>
     * he maximum number of stop orders available for a single trading pair in one account is 20.
     *
     * @param request
     * @return A response containing the order id.
     */
    OrderCreateResponse createStopOrder(StopOrderCreateRequest request) throws IOException;

    /**
     * Cancel a stop order which have not been triggered
     *
     * @param orderId
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelStopOrder(String orderId) throws IOException;

    /**
     * Cancel a stop order which have not been triggered
     *
     * @param clientOid
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelStopOrderByClientOid(String clientOid) throws IOException;

    /**
     * Cancel a bulk of stop orders which have not been triggered
     *
     * @param request
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelStopOrders(StopOrderCancelRequest request) throws IOException;

    /**
     * Get a single stop order which have not been triggered by order id and symbol.
     *
     * @param orderId
     * @return The requested stop order.
     */
    StopOrderResponse getStopOrder(String orderId) throws IOException;


    /**
     * Get a single stop order which have not been triggered by order id and symbol.
     *
     * @param clientOid
     * @param symbol [optional]
     * @return The requested stop order.
     */
    List<StopOrderResponse> getStopOrderByClientOid(String clientOid, String symbol) throws IOException;

    /**
     * Query un-triggered stop orders which requested.
     *
     * @param request
     * @return A page of orders.
     */
    Pagination<StopOrderResponse> queryStopOrders(StopOrderQueryRequest request) throws IOException;

}
