package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
@Data
public class HFMarginFillsResponse {

    private List<HFMarginTransactionRecord> items;

    private Long lastId;
}
