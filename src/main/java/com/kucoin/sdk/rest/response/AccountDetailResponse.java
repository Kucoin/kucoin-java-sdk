package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by tao.mao on 2018/11/15.
 */
@Data
public class AccountDetailResponse {

    private String currency;

    private BigDecimal amount;

    private BigDecimal fee;

    private BigDecimal balance;

    private String bizType;

    private String direction;

    private String createdAt;

    private String context;

}
