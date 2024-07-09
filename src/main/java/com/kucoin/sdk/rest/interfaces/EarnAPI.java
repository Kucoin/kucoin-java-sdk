package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.EarnHoldAssetQueryRequest;
import com.kucoin.sdk.rest.request.EarnOrderRedemptionRequest;
import com.kucoin.sdk.rest.request.EarnOrderRequest;
import com.kucoin.sdk.rest.request.EarnRedeemPreviewRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * Earn endpoint
 *
 * @author blazetan
 */
public interface EarnAPI {
    /**
     * This endpoint allows subscribing to fixed income products.
     *
     * @param request
     * @return
     */
    EarnOrderResponse createOrder(EarnOrderRequest request)  throws IOException;

    /**
     * This endpoint allows initiating redemption by holding ID.
     * If the current holding is fully redeemed or in the process of being redeemed, it indicates that the holding does not exist.
     *
     * @param request
     * @return
     */
    EarnOrderRedemptionResponse redemptionOrder(EarnOrderRedemptionRequest request) throws IOException;

    /**
     * This endpoint retrieves redemption preview information by holding ID.
     * If the current holding is fully redeemed or in the process of being redeemed, it indicates that the holding does not exist.
     *
     * @param request
     * @return
     */
    EarnRedeemPreviewResponse getRedeemPreview(EarnRedeemPreviewRequest request) throws IOException;

    /**
     * This endpoint retrieves savings products.
     * If no savings products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    List<EarnSavingProductResponse> getSavingProducts(String currency) throws IOException;

    /**
     * This endpoint retrieves current holding assets of fixed income products.
     * If no current holding assets are available, an empty list is returned.
     *
     * @param request
     * @return
     */
    Pagination<EarnHoldAssetResponse> getHoldAssets(EarnHoldAssetQueryRequest request) throws IOException;

    /**
     * This endpoint retrieves limited-time promotion products.
     * If no products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    List<EarnPromotionProductResponse> getPromotionProducts(String currency) throws IOException;

    /**
     * This endpoint retrieves KCS Staking products.
     * If no KCS Staking products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    List<EarnKcsStakingProductResponse> getKcsStakingProducts(String currency) throws IOException;

    /**
     * This endpoint retrieves staking products.
     * If no staking products are available, an empty list is returned.
     *
     * @param currency Subscription currency
     * @return
     */
    List<EarnStakingProductResponse> getStakingProducts(String currency) throws IOException;

    /**
     * This endpoint retrieves ETH Staking products.
     * If no ETH Staking products are available, an empty list is returned.
     *
     * @return
     */
    List<EarnEthStakingProductResponse> getEthStakingProducts() throws IOException;

}
