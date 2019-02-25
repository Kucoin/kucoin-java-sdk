/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kucoin.sdk.model.enums.DepositStatusEnum;

import lombok.Data;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositResponse {

    private String currency;

    private DepositStatusEnum status;

    private String address;

    private String memo;

    private Boolean isInner;

    private BigDecimal amount;

    private BigDecimal fee;

    private String walletTxId;

    private Long createdAt;

    private Long updatedAt;
}
