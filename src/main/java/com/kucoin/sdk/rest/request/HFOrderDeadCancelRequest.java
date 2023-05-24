package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderDeadCancelRequest.java
 * @Description
 * @createTime 2023/05/24日 14:27:00
 */
@Data
public class HFOrderDeadCancelRequest {
    private Integer timeout;
    private String symbols;
}
