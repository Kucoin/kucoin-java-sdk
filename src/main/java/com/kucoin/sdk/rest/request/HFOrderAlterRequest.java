package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderAlterRequest {
    private String symbol;
    private String clientOid;
    private String orderId;
    private String newPrice;
    private String newSize;
}
