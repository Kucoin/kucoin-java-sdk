/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.request.WithdrawApplyV3Request;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface WithdrawalAPIRetrofit {

    @GET("api/v1/withdrawals/quotas")
    Call<KucoinResponse<WithdrawQuotaResponse>> getWithdrawQuotas(@Query("currency") String currency,
                                                                  @Query("chain") String chain);

    @POST("api/v1/withdrawals")
    Call<KucoinResponse<WithdrawApplyResponse>> applyWithdraw(@Body WithdrawApplyRequest request);

    @POST("api/v3/withdrawals")
    Call<KucoinResponse<WithdrawApplyV3Response>> applyWithdrawV3(@Body WithdrawApplyV3Request request);

    @DELETE("api/v1/withdrawals/{withdrawalId}")
    Call<KucoinResponse<Void>> cancelWithdraw(@Path("withdrawalId") String withdrawalId);

    @GET("api/v1/withdrawals")
    Call<KucoinResponse<Pagination<WithdrawResponse>>> getWithdrawPageList(@Query("currentPage") int currentPage,
                                                                           @Query("pageSize") int pageSize,
                                                                           @Query("currency") String currency,
                                                                           @Query("status") String status,
                                                                           @Query("startAt") Long startAt,
                                                                           @Query("endAt") Long endAt);

    @GET("api/v1/hist-withdrawals")
    Call<KucoinResponse<Pagination<WithdrawResponse>>> getHistWithdrawPageList(@Query("currentPage") int currentPage,
                                                                               @Query("pageSize") int pageSize,
                                                                               @Query("currency") String currency,
                                                                               @Query("status") String status,
                                                                               @Query("startAt") Long startAt,
                                                                               @Query("endAt") Long endAt);
}
