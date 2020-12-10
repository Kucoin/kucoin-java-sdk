/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BorrowQueryResponse {

    private String orderId;

    private String currency;

    private BigDecimal size;

    private String status;

    private BigDecimal filled;

    private List<MatchItem> matchList;
}
