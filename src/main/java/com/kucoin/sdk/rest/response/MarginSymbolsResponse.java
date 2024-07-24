package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/24
 */
@Data
public class MarginSymbolsResponse {
    /**
     * server timestamp
     */
    private Long timestamp;
    /**
     * Trading Pairs
     */
    private List<SymbolV3Response> items;
}