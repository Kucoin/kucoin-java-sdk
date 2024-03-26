package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApiCurrencyDetailChainPropertyResponseV2 {

    private String chainName;

    private BigDecimal withdrawalMinSize;

    private BigDecimal withdrawalMinFee;

    private Boolean isWithdrawEnabled;

    private Boolean isDepositEnabled;

    private Integer confirms;

    private Integer preConfirms;

    private String contractAddress;

    private String chainId;
}