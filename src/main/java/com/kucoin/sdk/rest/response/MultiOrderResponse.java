/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MultiOrderResponse {

    private String symbol;

    private String type = "limit";

    private String side;

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal funds;

    private String stp = "";

    private String stop = "";

    private BigDecimal stopPrice;


    private String timeInForce = "GTC";

    private long cancelAfter;

    private boolean postOnly = false;

    private boolean hidden = false;

    private boolean iceberge = false;

    private boolean iceberg = false;

    private BigDecimal visibleSize;

    private String channel;

    public void setIceberg(boolean iceberg) {
        if (!iceberge && iceberg) {
            this.iceberge = iceberg;
        }
    }

    private String id;

    private String status;

    private String failMsg;

    private String clientOid;
}
