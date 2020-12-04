/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

@Data
public class MultiOrderCreateResponse {

    private List<MultiOrderResponse> data;
}
