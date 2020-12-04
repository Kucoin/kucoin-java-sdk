/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActiveOrderResponse {

    private String id;

    private String symbol;

    private String opType;

    private String type;

    public String getType() {
        return this.type == null ? null : this.type.toLowerCase();
    }

    private String side;

    public String getSide() {
        return this.side == null ? null : this.side.toLowerCase();
    }

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal funds;

    private BigDecimal dealFunds;

    private BigDecimal dealSize;

    private BigDecimal fee;

    private String feeCurrency;

    private String stp;

    private String stop;

    public String getStop() {
        return this.stop == null ? null : this.stop.toLowerCase();
    }

    private Boolean stopTriggered;

    private BigDecimal stopPrice;

    private String timeInForce;

    private boolean postOnly;

    private boolean hidden;

    private boolean iceberg;

    private BigDecimal visibleSize;

    private Long cancelAfter;

    private String channel;

    private String clientOid;

    private String remark;

    private String tags;

    private Boolean isActive;

    private boolean cancelExist;

    private Date createdAt;

    private String tradeType;

    public String getTradeType() {
        if (StringUtils.isBlank(tradeType)) {
            return "TRADE";
        }
        return tradeType;
    }
}
