package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderDeadCancelRequest {
    private Integer timeout;
    private String symbols;
}
