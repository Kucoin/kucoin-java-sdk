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
 * Created by ezreal on 2020/12/08.
 */
public interface LoanAPIRetrofit {

    @POST("api/v3/margin/borrow")
    Call<KucoinResponse<BorrowV3Response>> borrowV3(@Body BorrowV3Request request);

    @GET("api/v3/margin/borrow")
    Call<KucoinResponse<Pagination<BorrowQueryV3Response>>> queryBorrowV3(@QueryMap Map<String, Object> request);

    @POST("api/v3/margin/repay")
    Call<KucoinResponse<RepayV3Response>> repayV3(@Body RepayV3Request request);

    @GET("api/v3/margin/repay")
    Call<KucoinResponse<Pagination<RepayQueryV3Response>>> queryRepayV3(@QueryMap Map<String, Object> request);

    @GET("api/v3/margin/interest")
    Call<KucoinResponse<Pagination<InterestQueryV3Response>>> queryInterestV3(@QueryMap Map<String, Object> request);

    @GET("api/v3/project/list")
    Call<KucoinResponse<List<MarginProjectListResponse>>> getProjectList(@Query("currency") String currency);

    @GET("api/v3/project/marketInterestRate")
    Call<KucoinResponse<List<MarginMarketInterestRateResponse>>> getProjectMarketInterestRate(@Query("currency") String currency);

    @POST("api/v3/purchase")
    Call<KucoinResponse<PurchaseResponse>> purchase(@Body PurchaseRequest request);

    @POST("api/v3/lend/purchase/update")
    Call<KucoinResponse<Void>> updatePurchase(@Body UpdatePurchaseRequest request);

    @GET("api/v3/purchase/orders")
    Call<KucoinResponse<Pagination<PurchaseQueryResponse>>> queryPurchase(@QueryMap Map<String, Object> request);

    @POST("api/v3/redeem")
    Call<KucoinResponse<RedeemResponse>> redeem(@Body RedeemRequest request);

    @GET("api/v3/redeem/orders")
    Call<KucoinResponse<Pagination<RedeemQueryResponse>>> queryRedeem(@QueryMap Map<String, Object> request);

    @Deprecated
    @POST("api/v1/margin/borrow")
    Call<KucoinResponse<BorrowResponse>> borrow(@Body BorrowRequest request);

    @Deprecated
    @GET("api/v1/margin/borrow")
    Call<KucoinResponse<BorrowQueryResponse>> queryBorrow(@Query("orderId") String orderId);

    @Deprecated
    @GET("api/v1/margin/borrow/outstanding")
    Call<KucoinResponse<Pagination<BorrowOutstandingResponse>>> queryBorrowOutstanding(
            @Query("currency") String currency,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @Deprecated
    @GET("api/v1/margin/borrow/repaid")
    Call<KucoinResponse<Pagination<BorrowRepaidResponse>>> queryBorrowRepaid(
            @Query("currency") String currency,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @Deprecated
    @POST("api/v1/margin/repay/all")
    Call<KucoinResponse<Void>> repayAll(@Body RepayAllRequest request);

    @Deprecated
    @POST("api/v1/margin/repay/single")
    Call<KucoinResponse<Void>> repaySingle(@Body RepaySingleRequest request);

    @Deprecated
    @POST("api/v1/margin/lend")
    Call<KucoinResponse<LendResponse>> lend(@Body LendRequest request);

    @Deprecated
    @DELETE("api/v1/margin/lend/{orderId}")
    Call<KucoinResponse<Void>> cancelLendOrder(@Path("orderId") String orderId);

    @Deprecated
    @POST("api/v1/margin/toggle-auto-lend")
    Call<KucoinResponse<Void>> toggleAutoLend(@Body ToggleAutoLendRequest request);

    @Deprecated
    @GET("api/v1/margin/lend/active")
    Call<KucoinResponse<Pagination<ActiveLendItem>>> queryActiveLend(
            @Query("currency") String currency,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @Deprecated
    @GET("api/v1/margin/lend/done")
    Call<KucoinResponse<Pagination<DoneLendItem>>> queryDoneLend(
            @Query("currency") String currency,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @Deprecated
    @GET("api/v1/margin/lend/trade/unsettled")
    Call<KucoinResponse<Pagination<UnsettledTradeItem>>> queryUnsettledTrade(
            @Query("currency") String currency,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @Deprecated
    @GET("api/v1/margin/lend/trade/settled")
    Call<KucoinResponse<Pagination<SettledTradeItem>>> querySettledTrade(
            @Query("currency") String currency,
            @Query("currentPage") Integer currentPage,
            @Query("pageSize") Integer pageSize);

    @Deprecated
    @GET("api/v1/margin/lend/assets")
    Call<KucoinResponse<List<LendAssetsResponse>>> queryLendAssets(@Query("currency") String currency);

    @Deprecated
    @GET("api/v1/margin/market")
    Call<KucoinResponse<List<MarketItemResponse>>> queryMarket(
            @Query("currency") String currency,
            @Query("term") Integer term);

    @Deprecated
    @GET("api/v1/margin/trade/last")
    Call<KucoinResponse<List<LastTradeResponse>>> queryLastTrade(@Query("currency") String currency);

}
