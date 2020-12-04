/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;


import lombok.Data;

import java.util.List;

@Data
public class MultiOrderCreateRequest {

    private String symbol;

    private List<OrderCreateApiRequest> orderList;

    private String channel;
}
