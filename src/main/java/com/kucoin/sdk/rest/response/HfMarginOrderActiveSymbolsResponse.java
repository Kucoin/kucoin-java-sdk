package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/24
 */
@Data
public class HfMarginOrderActiveSymbolsResponse {
    /**
     * List of trading pairs with active orders
     */
    private List<String> symbols;
    /**
     * Number of trading pairs with active orders
     */
    private String symbolSize;
}
