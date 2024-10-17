package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderMultiCreateResponse {
    private String orderId;
    private String clientOid;
    private Boolean success;
}
