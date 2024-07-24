package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Colt Han
 * @since 2024/7/25
 */
@Data
public class HFMarginOrderCancelByOrderIdResponse {
    /**
     * Order id of the cancelled order
     */
    private String orderId;
}
