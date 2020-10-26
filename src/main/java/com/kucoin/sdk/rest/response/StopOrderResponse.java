/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StopOrderResponse {

    private String id;

    private String symbol;

    private String userId;

    private String type;

    private String side;

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal funds;

    private String stp;

    private String timeInForce;

    private Long cancelAfter;

    private boolean postOnly;

    private boolean hidden;

    private boolean iceberg;

    private BigDecimal visibleSize;

    private String channel;

    private String clientOid;

    private String feeCurrency;

    private String tradeSource;

    private String tradeType;

    private String stop;

    private BigDecimal stopPrice;

    private String remark;

    private String tags;

    private Date createdAt;

    private Date stopTriggerTime;

}
