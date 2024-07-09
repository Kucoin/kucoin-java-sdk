/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Builder
@Data
public class EarnOrderRedemptionRequest {

    /**
     * Holding ID
     * Necessary
     */
    private String orderId;

    /**
     * Redemption amount
     * Necessary
     */
    private String amount;

    /**
     * Account type: MAIN (funding account), TRADE (spot trading account). This parameter is valid only when orderId=ETH2
     */
    private String fromAccountType;

    /**
     * Confirmation field for early redemption penalty: 1 (confirm early redemption, and the current holding will be fully redeemed).
     * This parameter is valid only for fixed-term products
     */
    private String confirmPunishRedeem;

    public Map<String, Object> getMapParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("amount", amount);
        if (StringUtils.isNotBlank(fromAccountType)) {
            params.put("fromAccountType", fromAccountType);
        }
        if (StringUtils.isNotBlank(confirmPunishRedeem)) {
            params.put("confirmPunishRedeem", confirmPunishRedeem);
        }
        return params;
    }

}
