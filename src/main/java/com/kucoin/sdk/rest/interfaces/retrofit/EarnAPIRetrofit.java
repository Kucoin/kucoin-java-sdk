/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.EarnOrderRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * @author blazetan
 */
public interface EarnAPIRetrofit {

    /**
     * This endpoint allows subscribing to fixed income products.
     *
     * @param request
     * @return
     */
    @POST("api/v1/earn/orders")
    Call<KucoinResponse<EarnOrderResponse>> createOrder(@Body EarnOrderRequest request);

    /**
     * This endpoint allows initiating redemption by holding ID.
     * If the current holding is fully redeemed or in the process of being redeemed, it indicates that the holding does not exist.
     *
     * @param params see EarnOrderDeleteRequest
     * @return
     */
    @DELETE("api/v1/earn/orders")
    Call<KucoinResponse<EarnOrderRedemptionResponse>> deleteOrder(@QueryMap Map<String, Object> params);

    /**
     * This endpoint retrieves redemption preview information by holding ID.
     * If the current holding is fully redeemed or in the process of being redeemed, it indicates that the holding does not exist.
     *
     * @param params see EarnRedeemPreviewRequest
     * @return
     */
    @GET("api/v1/earn/redeem-preview")
    Call<KucoinResponse<EarnRedeemPreviewResponse>> getRedeemPreview(@QueryMap Map<String, Object> params);

    /**
     * This endpoint retrieves savings products.
     * If no savings products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    @GET("api/v1/earn/saving/products")
    Call<KucoinResponse<List<EarnSavingProductResponse>>> getSavingProducts(@Query("currency") String currency);

    /**
     * This endpoint retrieves current holding assets of fixed income products.
     * If no current holding assets are available, an empty list is returned.
     *
     * @param params see EarnHoldAssetQueryRequest
     * @return
     */
    @GET("api/v1/earn/hold-assets")
    Call<KucoinResponse<Pagination<EarnHoldAssetResponse>>> getHoldAssets(@QueryMap Map<String, Object> params);

    /**
     * This endpoint retrieves limited-time promotion products.
     * If no products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    @GET("api/v1/earn/promotion/products")
    Call<KucoinResponse<List<EarnPromotionProductResponse>>> getPromotionProducts(@Query("currency") String currency);

    /**
     * This endpoint retrieves KCS Staking products.
     * If no KCS Staking products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    @GET("api/v1/earn/kcs-staking/products")
    Call<KucoinResponse<List<EarnKcsStakingProductResponse>>> getKcsStakingProducts(@Query("currency") String currency);

    /**
     * This endpoint retrieves staking products.
     * If no staking products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    @GET("api/v1/earn/staking/products")
    Call<KucoinResponse<List<EarnStakingProductResponse>>> getStakingProducts(@Query("currency") String currency);

    /**
     * This endpoint retrieves ETH Staking products.
     * If no ETH Staking products are available, an empty list is returned.
     *
     * @return
     */
    @GET("api/v1/earn/eth-staking/products")
    Call<KucoinResponse<List<EarnEthStakingProductResponse>>> getEthStakingProducts();

}
