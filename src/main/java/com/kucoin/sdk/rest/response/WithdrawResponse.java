/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kucoin.sdk.model.enums.WithdrawStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawResponse {

    private String id;

    private String currency;

    private WithdrawStatusEnum status;

    private String address;

    private String memo;

    @JsonProperty("isInner")
    private Boolean isInner;

    private BigDecimal amount;

    private BigDecimal fee;

    private String walletTxId;

    private Date createdAt;

    private Date updatedAt;

    private String remark;
}
