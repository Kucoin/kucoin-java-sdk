/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositAddressResponse {

    private String address;

    private String memo;

    private String chain;

    private String chainId;

    private String to;

    private String currency;

    private String contractAddress;

}
