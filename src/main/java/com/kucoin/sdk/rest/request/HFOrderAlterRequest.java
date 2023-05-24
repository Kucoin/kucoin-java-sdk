package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderAlterRequest.java
 * @Description
 * @createTime 2023/05/23日 15:50:00
 */
@Data
public class HFOrderAlterRequest {
    private String symbol;
    private String clientOid;
    private String orderId;
    private String newPrice;
    private String newSize;
}
