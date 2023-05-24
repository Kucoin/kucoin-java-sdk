package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderDeadCancelQueryResponse.java
 * @Description
 * @createTime 2023/05/23日 16:41:00
 */
@Data
public class HFOrderDeadCancelQueryResponse {
    private String timeout;
    private String symbols;
    private String currentTime;
    private String triggerTime;
}
