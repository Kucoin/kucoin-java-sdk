package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by tao.mao on 2018/11/15.
 */
@Data
public class AccountHoldsResponse {

    private String currency;

    private BigDecimal holdAmount;

    private String bizType;

    private String orderId;

    private String createdAt;

    private String updatedAt;
}
