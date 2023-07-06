package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class IsolatedAssetResponse {
    private String symbol;

    private String status;

    private String debtRatio;

    private IsolatedAssetBaseInfo baseAsset;

    private IsolatedAssetBaseInfo quoteAsset;
}
