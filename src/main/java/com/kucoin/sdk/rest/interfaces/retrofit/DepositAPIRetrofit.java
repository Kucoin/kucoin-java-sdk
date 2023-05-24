/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.DepositAddressCreateRequest;
import com.kucoin.sdk.rest.response.DepositAddressResponse;
import com.kucoin.sdk.rest.response.DepositResponse;
import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.Pagination;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface DepositAPIRetrofit {

    @POST("api/v1/deposit-addresses")
    Call<KucoinResponse<DepositAddressResponse>> createDepositAddress(@Body DepositAddressCreateRequest request);

    @GET("api/v1/deposit-addresses")
    Call<KucoinResponse<DepositAddressResponse>> getDepositAddress(@Query("currency") String currency,
                                                                   @Query(("chain")) String chain);

    @GET("api/v1/deposits")
    Call<KucoinResponse<Pagination<DepositResponse>>> getDepositPageList(@Query("currentPage") int currentPage,
                                                                         @Query("pageSize") int pageSize,
                                                                         @Query("currency") String currency,
                                                                         @Query("status") String status,
                                                                         @Query("startAt") Long startAt,
                                                                         @Query("endAt") Long endAt);

    @GET("api/v2/deposit-addresses")
    Call<KucoinResponse<List<DepositAddressResponse>>> getDepositAddresses(@Query("currency") String currency);

    @GET("api/v1/hist-deposits")
    Call<KucoinResponse<Pagination<DepositResponse>>> getHistDepositPageList(@Query("currentPage") int currentPage,
                                                                             @Query("pageSize") int pageSize,
                                                                             @Query("currency") String currency,
                                                                             @Query("status") String status,
                                                                             @Query("startAt") Long startAt,
                                                                             @Query("endAt") Long endAt);
}
