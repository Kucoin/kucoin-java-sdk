

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
public class WithdrawQuotaResponse {

    private String currency;

    private BigDecimal availableAmount;

    private BigDecimal remainAmount;

    private BigDecimal withdrawMinSize;

    private BigDecimal limitBTCAmount;

    private BigDecimal innerWithdrawMinFee;

    private BigDecimal usedBTCAmount;

    private Boolean isWithdrawEnabled;

    private BigDecimal withdrawMinFee;

    private Integer precision;

}
