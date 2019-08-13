/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.AccountCreateRequest;
import com.kucoin.sdk.rest.request.AccountTransferRequest;
import com.kucoin.sdk.rest.request.SubMasterTransferRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface AccountAPIRetrofit {

    @GET("api/v1/accounts")
    Call<KucoinResponse<List<AccountBalancesResponse>>> getAccountList(
            @Query("currency") String currency, @Query("type") String type);

    @GET("api/v1/accounts/{accountId}")
    Call<KucoinResponse<AccountBalanceResponse>> getAccount(@Path("accountId") String accountId);

    @POST("api/v1/accounts")
    Call<KucoinResponse<Map<String, String>>> addAccount(@Body AccountCreateRequest request);

    @GET("api/v1/accounts/{accountId}/ledgers")
    Call<KucoinResponse<Pagination<AccountDetailResponse>>> getAccountDetail(
            @Path("accountId") String accountId,
            @Query("currentPage") int currentPage,
            @Query("pageSize") int pageSize,
            @Query("startAt") long startAt,
            @Query("endAt") long endAt);

    @GET("api/v1/accounts/{accountId}/holds")
    Call<KucoinResponse<Pagination<AccountHoldsResponse>>> getAccountHold(
            @Path("accountId") String accountId,
            @Query("currentPage") int currentPage,
            @Query("pageSize") int pageSize);

    @POST("api/v1/accounts/inner-transfer")
    Call<KucoinResponse<Map<String, String>>> applyTransfer(
            @Body AccountTransferRequest request);

    @GET("api/v1/sub-accounts")
    Call<KucoinResponse<List<SubAccountBalanceResponse>>> getSubAccountList();

    @GET("api/v1/sub-accounts/{subUserId}")
    Call<KucoinResponse<SubAccountBalanceResponse>> getSubAccount(@Path("subUserId") String subUserId);

    @POST("api/v1/accounts/sub-transfer")
    Call<KucoinResponse<Map<String, String>>> transferBetweenSubAndMaster(@Body SubMasterTransferRequest request);
}
