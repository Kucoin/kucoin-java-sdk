/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


@Builder
@Data
public class EarnRedeemPreviewRequest {

    /**
     * Holding ID
     * Necessary
     */
    private String orderId;


    /**
     * Account type: MAIN (funding account), TRADE (spot trading account). This parameter is valid only when orderId=ETH2
     */
    private String fromAccountType;

    public Map<String, Object> getMapParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        if (StringUtils.isNotBlank(fromAccountType)) {
            params.put("fromAccountType", fromAccountType);
        }
        return params;
    }

}
