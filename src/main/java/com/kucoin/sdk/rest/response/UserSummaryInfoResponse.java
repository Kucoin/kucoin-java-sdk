package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName UserSummaryInfoResponse.java
 * @Description
 * @createTime 2023/05/16日 12:02:00
 */
@Data
public class UserSummaryInfoResponse {
    String level;

    String subQuantity;

    String maxDefaultSubQuantity;

    String maxSubQuantity;

    String spotSubQuantity;

    String marginSubQuantity;

    String futuresSubQuantity;

    String maxSpotSubQuantity;

    String maxMarginSubQuantity;

    String maxFuturesSubQuantity;
}
