package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderSyncCreateResponse {
    private String orderId;
    private String clientOid;
    private String orderTime;
    private String originSize;
    private String dealSize;
    private String remainSize;
    private String canceledSize;
    private String status;
    private String matchTime;
}
