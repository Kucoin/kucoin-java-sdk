package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Reeta on 2019/9/29
 */
@Data
public class TransferableBalanceResponse {

    private String currency;

    private BigDecimal balance;

    private BigDecimal available;

    private BigDecimal holds;

    private BigDecimal transferable;

}
