package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderResponse {
    private String id;
    private String symbol;
    private String opType;
    private String type;
    private String side;
    private String price;
    private String size;
    private String cancelledSize;
    private String dealSize;
    private String remainSize;
    private String funds;
    private String cancelledFunds;
    private String dealFunds;
    private String remainFunds;
    private String fee;
    private String feeCurrency;
    private String stp;
    private String timeInForce;
    private Boolean postOnly;
    private Boolean hidden;
    private Boolean iceberg;
    private String visibleSize;
    private String cancelAfter;
    private String channel;
    private String clientOid;
    private String remark;
    private String tags;
    private Boolean active;
    private Boolean inOrderBook;
    private Boolean cancelExist;
    private String createdAt;
    private String lastUpdatedAt;
    private String tradeType;
}
