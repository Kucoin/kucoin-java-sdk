/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.OcoOrderCancelRequest;
import com.kucoin.sdk.rest.request.OcoOrderCreateRequest;
import com.kucoin.sdk.rest.request.OcoOrderQueryRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

/**
 * @author blazetan
 */
public interface OcoOrderAPI {

    /**
     * Place a new oco order
     * <p>
     * maximum number of oco orders available for a single trading pair in one account is 20.
     *
     * @param request
     * @return A response containing the order id.
     */
    OrderCreateResponse createOcoOrder(OcoOrderCreateRequest request) throws IOException;

    /**
     * Cancel an oco order which have not been triggered
     *
     * @param orderId
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelOcoOrder(String orderId) throws IOException;

    /**
     * Cancel an oco order which have not been triggered
     *
     * @param clientOid
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelOcoOrderByClientOid(String clientOid) throws IOException;

    /**
     * Cancel a bulk of oco orders which have not been triggered
     *
     * @param request
     * @return A response containing the id of the cancelled order.
     */
    OrderCancelResponse cancelOcoOrders(OcoOrderCancelRequest request) throws IOException;

    /**
     * Get a single oco order by order id
     *
     * @param orderId
     * @return The requested oco order.
     */
    OcoOrderResponse getOcoOrder(String orderId) throws IOException;

    /**
     * Get Order Details by orderId
     * @param orderId
     * @return OcoOrderDetailResponse
     * @throws IOException
     */
    OcoOrderDetailResponse getOcoOrderDetails(String orderId) throws IOException;

    /**
     * Get a single stop order by order id.
     *
     * @param clientOid
     * @return The requested stop order.
     */
    OcoOrderResponse getOcoOrderByClientOid(String clientOid) throws IOException;

    /**
     * Query oco orders which requested.
     *
     * @param request
     * @return A page of orders.
     */
    Pagination<OcoOrderResponse> queryOcoOrders(OcoOrderQueryRequest request) throws IOException;

}
