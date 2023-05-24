package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderDeadCancelResponse.java
 * @Description
 * @createTime 2023/05/23日 16:39:00
 */
@Data
public class HFOrderDeadCancelResponse {
    private String currentTime;
    private String triggerTime;
}
