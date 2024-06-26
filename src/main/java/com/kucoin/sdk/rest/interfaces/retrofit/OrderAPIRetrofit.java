/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface OrderAPIRetrofit {

    @POST("api/v1/orders")
    Call<KucoinResponse<OrderCreateResponse>> createOrder(@Body OrderCreateApiRequest opsRequest);

    @POST("api/v1/orders/test")
    Call<KucoinResponse<OrderCreateResponse>> createOrderTest(@Body OrderCreateApiRequest opsRequest);

    @POST("api/v1/orders/multi")
    Call<KucoinResponse<MultiOrderCreateResponse>> createMultipleOrders(@Body MultiOrderCreateRequest multiOrderCreateRequest);

    @DELETE("api/v1/orders/{orderId}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrder(@Path("orderId") String orderId);

    @DELETE("api/v1/order/client-order/{clientOid}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrderByClientOid(@Path("clientOid") String clientOid);

    @DELETE("api/v1/orders")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrders(@Query("symbol") String symbol,
                                                           @Query("tradeType") String tradeType);


    @GET("api/v1/orders/{orderId}")
    Call<KucoinResponse<OrderResponse>> getOrder(@Path("orderId") String orderId);

    @GET("api/v1/order/client-order/{clientOid}")
    Call<KucoinResponse<ActiveOrderResponse>> getOrderByClientOid(@Path("clientOid") String clientOid);

    @GET("api/v1/orders")
    Call<KucoinResponse<Pagination<OrderResponse>>> queryOrders(@Query("symbol") String symbol,
                                                                @Query("side") String side,
                                                                @Query("type") String type,
                                                                @Query("tradeType") String tradeType,
                                                                @Query("status") String status,
                                                                @Query("startAt") Long startAt,
                                                                @Query("endAt") Long endAt,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("currentPage") int currentPage);

    @GET("api/v1/trade-fees")
    Call<KucoinResponse<List<UserFeeResponse>>> getUserTradeFees(@Query("symbols") String symbols);

    @GET("api/v1/base-fee")
    Call<KucoinResponse<UserFeeResponse>> getUserBaseFee(@Query("currencyType") String currencyType);

    @Deprecated
    @GET("api/v1/limit/orders")
    Call<KucoinResponse<Pagination<OrderResponse>>> queryLimitOrderPageList(@Query("pageSize") int pageSize,
                                                                            @Query("currentPage") int currentPage);

    @GET("api/v1/limit/orders")
    Call<KucoinResponse<List<OrderResponse>>> queryLimitOrderList();

    @POST("api/v1/hf/orders")
    Call<KucoinResponse<HFOrderCreateResponse>> createHFOrder(@Body HFOrderCreateRequest createRequest);

    @POST("api/v1/hf/orders/test")
    Call<KucoinResponse<HFOrderCreateResponse>> createHFOrderTest(@Body HFOrderCreateRequest createRequest);

    @POST("api/v1/hf/orders/sync")
    Call<KucoinResponse<HFOrderSyncCreateResponse>> syncCreateHFOrder(@Body HFOrderCreateRequest createRequest);

    @POST("api/v1/hf/orders/multi")
    Call<KucoinResponse<List<HFOrderMultiCreateResponse>>> createMultipleHFOrders(@Body HFOrderMultiCreateRequest multiCreateRequest);

    @POST("api/v1/hf/orders/multi/sync")
    Call<KucoinResponse<List<HFOrderSyncMultiCreateResponse>>> syncCreateMultipleHFOrders(@Body HFOrderMultiCreateRequest multiCreateRequest);

    @POST("api/v1/hf/orders/alter")
    Call<KucoinResponse<HFOrderAlterResponse>> alterHFOrder(@Body HFOrderAlterRequest alterRequest);

    @DELETE("api/v1/hf/orders/{orderId}")
    Call<KucoinResponse<HFOrderCancelResponse>> cancelHFOrder(@Path("orderId") String orderId,
                                                              @Query("symbol") String symbol);

    @DELETE("api/v1/hf/orders/sync/{orderId}")
    Call<KucoinResponse<HFOrderSyncCancelResponse>> syncCancelHFOrder(@Path("orderId") String orderId,
                                                                      @Query("symbol") String symbol);

    @DELETE("api/v1/hf/orders/client-order/{clientOid}")
    Call<KucoinResponse<HFOrderCancelByClientOidResponse>> cancelHFOrderByClientOid(@Path("clientOid") String clientOid,
                                                                                    @Query("symbol") String symbol);

    @DELETE("api/v1/hf/orders/sync/client-order/{clientOid}")
    Call<KucoinResponse<HFOrderSyncCancelResponse>> syncCancelHFOrderByClientOid(@Path("clientOid") String clientOid,
                                                                                 @Query("symbol") String symbol);

    @DELETE("api/v1/hf/orders/cancel/{orderId}")
    Call<KucoinResponse<HFOrderCancelSizeResponse>> cancelHFOrderSize(@Path("orderId") String orderId, @Query("symbol") String symbol, @Query("cancelSize") String cancelSize);

    @DELETE("api/v1/hf/orders")
    Call<KucoinResponse<String>> cancelHFOrdersBySymbol(@Query("symbol") String symbol);

    @DELETE("api/v1/hf/orders/cancelAll")
    Call<KucoinResponse<HFOrderCancelAllResponse>> cancelAllHFOrders();

    @GET("api/v1/hf/orders/active")
    Call<KucoinResponse<List<HFOrderResponse>>> getActiveHFOrders(@Query("symbol") String symbol);

    @GET("api/v1/hf/orders/active/symbols")
    Call<KucoinResponse<HFOrderActiveSymbolQueryResponse>> getActiveHFOrderSymbols();

    @GET("api/v1/hf/orders/done")
    Call<KucoinResponse<HFDoneOrderQueryResponse>> getDoneHFOrders(@Query("symbol") String symbol,
                                                                   @Query("side") String side,
                                                                   @Query("type") String type,
                                                                   @Query("startAt") Long startAt,
                                                                   @Query("endAt") Long endAt,
                                                                   @Query("lastId") Long lastId,
                                                                   @Query("limit") Integer limit);

    @GET("api/v1/hf/orders/{orderId}")
    Call<KucoinResponse<HFOrderResponse>> getHFOrder(@Path("orderId") String orderId, @Query("symbol") String symbol);

    @GET("api/v1/hf/orders/client-order/{clientOid}")
    Call<KucoinResponse<HFOrderResponse>> getHFOrderByClientOid(@Path("clientOid") String clientOid,
                                                                @Query("symbol") String symbol);

    @POST("api/v1/hf/orders/dead-cancel-all")
    Call<KucoinResponse<HFOrderDeadCancelResponse>> deadCancelHFOrder(@Body HFOrderDeadCancelRequest request);

    @GET("api/v1/hf/orders/dead-cancel-all/query")
    Call<KucoinResponse<HFOrderDeadCancelQueryResponse>> queryHFOrderDeadCancel();

    @GET("/api/v1/status")
    Call<KucoinResponse<ServerStatusResponse>> queryServerStatus();
}
