package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.EarnAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.EarnAPIRetrofit;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * @author blazetan
 */
public class EarnAPIAdapter extends AuthRetrofitAPIImpl<EarnAPIRetrofit> implements EarnAPI {

    public EarnAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public EarnOrderResponse createOrder(EarnOrderRequest request) throws IOException {
        return super.executeSync(getAPIImpl().createOrder(request));
    }

    @Override
    public EarnOrderRedemptionResponse redemptionOrder(EarnOrderRedemptionRequest request) throws IOException {
        return super.executeSync(getAPIImpl().deleteOrder(request.getMapParams()));
    }

    @Override
    public EarnRedeemPreviewResponse getRedeemPreview(EarnRedeemPreviewRequest request) throws IOException {
        return super.executeSync(getAPIImpl().getRedeemPreview(request.getMapParams()));
    }

    @Override
    public List<EarnSavingProductResponse> getSavingProducts(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getSavingProducts(currency));
    }

    @Override
    public Pagination<EarnHoldAssetResponse> getHoldAssets(EarnHoldAssetQueryRequest request) throws IOException {
        return super.executeSync(getAPIImpl().getHoldAssets(request.getMapParams()));
    }

    @Override
    public List<EarnPromotionProductResponse> getPromotionProducts(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getPromotionProducts(currency));
    }

    @Override
    public List<EarnKcsStakingProductResponse> getKcsStakingProducts(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getKcsStakingProducts(currency));
    }

    @Override
    public List<EarnStakingProductResponse> getStakingProducts(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getStakingProducts(currency));
    }

    @Override
    public List<EarnEthStakingProductResponse> getEthStakingProducts() throws IOException {
        return super.executeSync(getAPIImpl().getEthStakingProducts());
    }

}
