package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by tao.mao on 2018/11/15.
 */
@Data
public class AccountBalancesResponse {

    private String id;

    private String currency;

    private String type;

    private BigDecimal balance;

    private BigDecimal available;

    private BigDecimal holds;

}
