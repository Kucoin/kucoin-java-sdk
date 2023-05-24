package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFTradeResponse.java
 * @Description
 * @createTime 2023/05/23日 16:45:00
 */
@Data
public class HFTradeResponse {
    private List<TradeResponse> items;
    private String lastId;
}
