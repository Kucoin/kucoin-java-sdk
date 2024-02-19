package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class IsolatedAccountV3Response {
    private List<Asset> assets;
    private BigDecimal totalLiabilityOfQuoteCurrency;
    private BigDecimal totalAssetOfQuoteCurrency;
    private Long timestamp;
    @Data
    public static class Asset {
        private String symbol;
        private BigDecimal debtRatio;
        private AssetDetail baseAsset;
        private AssetDetail quoteAsset;
        private String status;
    }

    @Data
    public static class AssetDetail {
        private String currency;
        private BigDecimal totalAsset;
        private BigDecimal available;
        private BigDecimal maxBorrowSize;
        private BigDecimal borrowed;
        private BigDecimal hold;
        private boolean borrowEnabled;
        private boolean repayEnabled;
        private boolean transferEnabled;
    }
}
