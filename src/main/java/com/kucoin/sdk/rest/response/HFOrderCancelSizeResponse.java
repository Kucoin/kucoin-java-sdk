package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderCancelSizeResponse {
    private String orderId;
    private String cancelSize;
}
