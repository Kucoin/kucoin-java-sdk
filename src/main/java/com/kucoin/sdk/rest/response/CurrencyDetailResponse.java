package com.kucoin.sdk.rest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrencyDetailResponse extends CurrencyResponse {
    private BigDecimal withdrawalMinSize;

    private BigDecimal withdrawalMinFee;

    private Boolean isWithdrawEnabled;

    private Boolean isDepositEnabled;
}
