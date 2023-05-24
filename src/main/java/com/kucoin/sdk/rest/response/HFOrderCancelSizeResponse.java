package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderCancelSizeResponse.java
 * @Description
 * @createTime 2023/05/23日 16:12:00
 */
@Data
public class HFOrderCancelSizeResponse {
    private String orderId;
    private String cancelSize;
}
