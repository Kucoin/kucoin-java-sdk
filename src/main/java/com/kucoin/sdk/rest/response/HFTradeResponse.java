package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFTradeResponse {
    private List<TradeResponse> items;
    private String lastId;
}
