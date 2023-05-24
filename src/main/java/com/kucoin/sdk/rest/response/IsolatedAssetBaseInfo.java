package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName IsolatedBaseAssetResponse.java
 * @Description
 * @createTime 2023/05/17日 11:31:00
 */
@Data
public class IsolatedAssetBaseInfo {
    private String currency;

    private String totalBalance;

    private String holdBalance;

    private String availableBalance;

    private String liability;

    private String interest;

    private String borrowableAmount;
}
