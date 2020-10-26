/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StopOrderCreateRequest {

    private String symbol;

    private String type = "limit";

    private String side;

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal funds;

    private String tradeType = "TRADE";

    private String stp;

    private String stop = "loss";

    private BigDecimal stopPrice;

    private String timeInForce = "GTC";

    private boolean postOnly;

    private boolean hidden;

    private boolean iceberg;

    private BigDecimal visibleSize;

    private Long cancelAfter;

    private String clientOid;

    private String remark;

    private String tags;

}
