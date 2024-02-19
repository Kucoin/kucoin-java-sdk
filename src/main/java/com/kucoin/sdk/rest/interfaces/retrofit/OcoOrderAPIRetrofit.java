/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.OcoOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author blazetan
 */
public interface OcoOrderAPIRetrofit {

    @POST("api/v3/oco/order")
    Call<KucoinResponse<OrderCreateResponse>> createOcoOrder(@Body OcoOrderCreateRequest request);

    @DELETE("api/v3/oco/order/{orderId}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOcoOrderByOrderId(@Path("orderId") String orderId);

    @DELETE("api/v3/oco/client-order/{clientOid}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOcoOrderByClientOid(@Path("clientOid") String clientOid);

    @DELETE("api/v3/oco/orders")
    Call<KucoinResponse<OrderCancelResponse>> cancelOcoOrders(@QueryMap Map<String, String> params);

    @GET("api/v3/oco/order/{orderId}")
    Call<KucoinResponse<OcoOrderResponse>> getOcoOrderByOrderId(@Path("orderId") String orderId);

    @GET("api/v3/oco/client-order/{clientOid}")
    Call<KucoinResponse<OcoOrderResponse>> getOcoOrderByClientOid(@Path("clientOid") String clientOid);

    @GET("api/v3/oco/orders")
    Call<KucoinResponse<Pagination<OcoOrderResponse>>> getOcoOrders(@QueryMap Map<String, String> params);

    @GET("api/v3/oco/order/details/{orderId}")
    Call<KucoinResponse<OcoOrderDetailResponse>> getOcoOrderDetails(@Path("orderId") String orderId);

}
