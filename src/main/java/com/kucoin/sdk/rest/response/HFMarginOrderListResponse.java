package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
@Data
public class HFMarginOrderListResponse {
    /**
     * Order id，a unique identifier pertaining to the order
     */
    private Long lastId;
    /**
     * Trading pair
     */
    private List<HFMarginOrderResponse> items;
}
