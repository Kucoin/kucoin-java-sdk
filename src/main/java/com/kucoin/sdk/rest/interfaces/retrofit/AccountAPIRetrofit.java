/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

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

    @GET("api/v1/accounts/ledgers")
    Call<KucoinResponse<Pagination<AccountDetailResponse>>> getAccountLedgers(
            @Query("currency") String currency,
            @Query("direction") String direction,
            @Query("bizType") String bizType,
            @Query("currentPage") int currentPage,
            @Query("pageSize") int pageSize,
            @Query("startAt") long startAt,
            @Query("endAt") long endAt);

    @POST("api/v2/accounts/inner-transfer")
    Call<KucoinResponse<Map<String, String>>> applyTransfer2(
            @Body AccountTransferV2Request request);

    @POST("api/v3/accounts/universal-transfer")
    Call<KucoinResponse<UniversalTransferResponse>> universalTransfer(@Body UniversalTransferRequest request);

    @GET("api/v1/sub-accounts")
    Call<KucoinResponse<List<SubAccountBalanceResponse>>> getSubAccountList();

    @GET("api/v1/sub-accounts/{subUserId}")
    Call<KucoinResponse<SubAccountBalanceResponse>> getSubAccount(@Path("subUserId") String subUserId);

    @POST("api/v2/accounts/sub-transfer")
    Call<KucoinResponse<Map<String, String>>> transferBetweenSubAndMasterV2(@Body SubMasterTransferV2Request request);

    @GET("api/v1/accounts/transferable")
    Call<KucoinResponse<TransferableBalanceResponse>> transferable(@Query("currency") String currency, @Query("type") String type, @Query("tag") String tag);

    @GET("api/v2/user-info")
    Call<KucoinResponse<UserSummaryInfoResponse>> getUserSummaryInfo();

    @POST("api/v2/sub/user/created")
    Call<KucoinResponse<SubUserCreateResponse>> createSubUser(@Body SubUserCreateRequest request);

    @GET("api/v1/sub/api-key")
    Call<KucoinResponse<List<SubApiKeyResponse>>> getSubApiKey(@Query("subName") String subName, @Query("apiKey") String apiKey);

    @POST("api/v1/sub/api-key")
    Call<KucoinResponse<SubApiKeyResponse>> createSubApiKey(@Body SubApiKeyCreateRequest request);

    @POST("api/v1/sub/api-key/update")
    Call<KucoinResponse<SubApiKeyResponse>> updateSubApiKey(@Body SubApiKeyUpdateRequest request);

    @DELETE("api/v1/sub/api-key")
    Call<KucoinResponse<SubApiKeyResponse>> deleteSubApiKey(@QueryMap Map<String, String> params);

    @GET("api/v2/sub-accounts")
    Call<KucoinResponse<Pagination<SubAccountBalanceResponse>>> getSubAccountPageList(@Query("currentPage") int currentPage,
                                                                                      @Query("pageSize") int pageSize);

    @GET("api/v1/hf/accounts/ledgers")
    Call<KucoinResponse<List<AccountDetailResponse>>> getHFAccountLedgers(@Query("currency") String currency,
                                                                          @Query("direction") String direction,
                                                                          @Query("bizType") String bizType,
                                                                          @Query("lastId") Long lastId,
                                                                          @Query("limit") Integer limit,
                                                                          @Query("startAt") Long startAt,
                                                                          @Query("endAt") Long endAt);
}
