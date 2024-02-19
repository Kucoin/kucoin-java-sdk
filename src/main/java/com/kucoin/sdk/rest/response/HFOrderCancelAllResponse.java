package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author blazetan
 * @version 1.0.0
 */
@Data
public class HFOrderCancelAllResponse {

    private List<String> succeedSymbols;

    private List<HFCancelFailedOrders> failedSymbols;

    @Data
    public static class HFCancelFailedOrders{
        private String symbol;
        private String error;
    }

}
